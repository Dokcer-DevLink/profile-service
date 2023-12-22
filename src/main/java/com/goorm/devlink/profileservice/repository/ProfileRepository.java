package com.goorm.devlink.profileservice.repository;

import com.goorm.devlink.profileservice.dto.ProfileDto;
import com.goorm.devlink.profileservice.entity.ProfileEntity;
import com.goorm.devlink.profileservice.entity.ProfileType;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
public interface ProfileRepository extends JpaRepository<ProfileEntity, Long> {//, ProfileRepositoryCustom {

    // 마이프로필 조회 : RequestHeader(userUuid), RequestParamter(profileType)
//    ProfileEntity findByUserUuidAndProfileType(@Param("userUuid") String userUuid, @Param("profileType") ProfileType profileType);

    @Transactional
    ProfileEntity findByUserUuid(@Param("userUuid") String userUuid);

    // stack + address 검색 구현 실패
    // stack만 검색도 실패. Collection 타입은 String 검색이 안됨.
//    @Query("select p from ProfileEntity p where (p.stacks like %:keyword%)")// or p.address like %:keyword%)")
//    List<ProfileEntity> findProfilesByKeyword(@Param("keyword") String keyword);
    @Transactional
    @Query("SELECT DISTINCT p FROM Profi" +
            "leEntity p, IN (p.stacks) s WHERE s LIKE %:keyword% AND (p.profileType = :profileType OR p.profileType = 'BOTH')")
    List<ProfileEntity> findProfileListByStackKeyword(@Param("profileType") ProfileType profileType, @Param("keyword") String keyword);

    @Transactional
    @Query("SELECT DISTINCT p FROM ProfileEntity p, IN (p.stacks) s WHERE s LIKE %:keyword% AND (p.profileType = :profileType OR p.profileType = 'BOTH')") // s.stacks = :keyword
    Slice<ProfileEntity> findSliceByStackKeyword(@Param("profileType") ProfileType profileType, @Param("keyword") String keyword, Pageable pageable);

//    RequestHeader(userUuid), RequestParamter(profileUuid) 둘 다 필요한데 에러발생으로 우선 profileUuid만 사용
//    @Transactional
//    @Modifying(clearAutomatically = true)
//    @Query("delete from ProfileEntity p where (p.userUuid = :userUuid and p.profileUuid = :profileUuid)")
//    void deleteProfileByUserUuidAndProfileUuid(@Param("userUuid") String userUuid, @Param("profileUuid") String profileUuid);

    @Transactional
    @Modifying
    @Query("UPDATE ProfileEntity p " +
            "SET p.profileImageUrl = :#{#profileEntity.profileImageUrl}, p.name = :#{#profileEntity.name}, p.nickname = :#{#profileEntity.nickname}, " +
            "p.introduction = :#{#profileEntity.introduction}, p.career = :#{#profileEntity.career}, p.profileType = :#{#profileEntity.profileType}, " +
            "p.address = :#{#profileEntity.address}, p.stacks = :#{#profileEntity.stacks} WHERE p.userUuid = :#{#profileEntity.userUuid}")
    void updateByProfileEntity(@Param("profileEntity") ProfileEntity profileEntity);

    @Transactional
    void deleteProfileByUserUuid(String userUuid);
}
