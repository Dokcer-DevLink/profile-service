package com.goorm.devlink.profileservice.repository;

import com.goorm.devlink.profileservice.entity.ProfileEntity;
import com.goorm.devlink.profileservice.entity.constant.ProfileType;
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

    @Transactional(readOnly = true)
    ProfileEntity findByUserUuid(@Param("userUuid") String userUuid);

    @Transactional(readOnly = true)
    List<ProfileEntity> findByUserUuidIn(@Param("userUuidList") List<String> userUuidList);

    @Transactional(readOnly = true)
    @Query("SELECT DISTINCT p FROM ProfileEntity p, IN (p.stacks) s WHERE s LIKE %:keyword% AND (p.profileType = :profileType OR p.profileType = 'BOTH')")
    List<ProfileEntity> findProfileListByStackKeyword(@Param("profileType") ProfileType profileType, @Param("keyword") String keyword);

    @Transactional(readOnly = true)
    @Query("SELECT DISTINCT p FROM ProfileEntity p JOIN p.stacks s WHERE ((s LIKE %:keyword%) OR (p.nickname LIKE %:keyword%)) AND (p.profileType = :profileType OR p.profileType = 'BOTH')")
    Slice<ProfileEntity> findSliceByStackKeyword(@Param("profileType") ProfileType profileType, @Param("keyword") String keyword, Pageable pageable);

    @Transactional(readOnly = true)
    @Query("SELECT p FROM ProfileEntity p WHERE p.profileType = :profileType OR p.profileType = 'BOTH'")
    List<ProfileEntity> findListByProfileType(@Param("profileType") ProfileType profileType, Pageable pageable);

    @Transactional
    @Modifying
    @Query("UPDATE ProfileEntity p " +
            "SET p.profileImageUrl = :#{#profileEntity.profileImageUrl}, p.nickname = :#{#profileEntity.nickname}, " +
            "p.introduction = :#{#profileEntity.introduction}, p.profileType = :#{#profileEntity.profileType}, " +
            "p.address = :#{#profileEntity.address}, p.stacks = :#{#profileEntity.stacks} WHERE p.userUuid = :#{#profileEntity.userUuid}")
    void updateByProfileEntity(@Param("profileEntity") ProfileEntity profileEntity);

    @Transactional
    void deleteProfileByUserUuid(String userUuid);
}
