package com.goorm.devlink.profileservice.repository;

import com.goorm.devlink.profileservice.entity.ProfileEntity;
import com.goorm.devlink.profileservice.entity.ProfileType;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface ProfileRepository extends JpaRepository<ProfileEntity, Long>, ProfileRepositoryCustom {

    // 마이프로필 조회 : RequestHeader(userUuid), RequestParamter(profileType)
    ProfileEntity findByUserUuidAndProfileType(@Param("userUuid") String userUuid, @Param("profileType") ProfileType profileType);

    ProfileEntity findByUserUuid(@Param("userUuid") String userUuid);

//    @Query("select new com.goorm.devlink.profileservice.dto.ProfileDto(p.profileUuid, p.userUuid, p.profileImageUrl, p.name, p.nickname, p.introduction, p.stacks, p.address) " +
//            "from ProfileEntity p where p.profileUuid = :profileUuid")
//    ProfileDto findByProfileUuid(@Param("profileUuid") String profileUuid);
//
//    @Query("select new com.goorm.devlink.profileservice.dto.ProfileDto(p.profileUuid, p.userUuid, p.profileImageUrl, p.name, p.nickname, p.introduction, p.stacks, p.address) " +
//            "from ProfileEntity p where p.profileUuid = :profileUuid and p.userUuid = :userUuid")
//    ProfileDto findByProfileAndUserUuid(@Param("profileUuid") String profileUuid, @Param("userUuid") String userUuid);

//    @Query("select new com.goorm.devlink.profileservice.dto.ProfileDto" +
//            "(p.profileUuid, p.userUuid, p.profileImageUrl, p.name, p.nickname, p.profileType, p.introduction, p.career, p.address, p.stacks) " +
//            "from ProfileEntity p where (p.address like %:keyword% or p.address like %:keyword%)")
//    List<ProfileDto> findProfilesByKeyword(@Param("keyword") String keyword);

    // stack + address 검색 구현 실패
    // stack만 검색도 실패. Collection 타입은 String 검색이 안됨.
//    @Query("select p from ProfileEntity p where (p.stacks like %:keyword%)")// or p.address like %:keyword%)")
//    List<ProfileEntity> findProfilesByKeyword(@Param("keyword") String keyword);
    @Query("SELECT DISTINCT p FROM ProfileEntity p, IN (p.stacks) s WHERE s LIKE %:keyword% AND p.profileType = :profileType")
    List<ProfileEntity> findProfileListByStackKeyword(@Param("profileType") ProfileType profileType, @Param("keyword") String keyword);

    @Query("SELECT DISTINCT p FROM ProfileEntity p, IN (p.stacks) s WHERE s LIKE %:keyword% AND p.profileType = :profileType") // s.stacks = :keyword
    Slice<ProfileEntity> findSliceByStackKeyword(@Param("profileType") ProfileType profileType, @Param("keyword") String keyword, Pageable pageable);

//    RequestHeader(userUuid), RequestParamter(profileUuid) 둘 다 필요한데 에러발생으로 우선 profileUuid만 사용
//    @Transactional
//    @Modifying(clearAutomatically = true)
//    @Query("delete from ProfileEntity p where (p.userUuid = :userUuid and p.profileUuid = :profileUuid)")
//    void deleteProfileByUserUuidAndProfileUuid(@Param("userUuid") String userUuid, @Param("profileUuid") String profileUuid);
    @Transactional
    void deleteProfileByUserUuid(String userUuid);
}
