package com.goorm.devlink.profileservice.repository;

import com.goorm.devlink.profileservice.dto.ProfileDto;
import com.goorm.devlink.profileservice.entity.ProfileEntity;
import com.goorm.devlink.profileservice.entity.ProfileType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface ProfileRepository extends JpaRepository<ProfileEntity, Long> {

//    @Query("select new com.goorm.devlink.profileservice.dto.ProfileDto(p.id, p.nickname) from ProfileEntity p")
//    ProfileDto findProfileDtoById(Long id);

//    @Query("select new com.goorm.devlink.profileservice.dto.ProfileDto(p.profileImageUrl, p.name, p.nickname, p.introduction, p.career, p.stacks, p.address) from ProfileEntity p where p.id = :id")
//    ProfileDto findProfileDtoById(@Param("id") Long id);

    // 마이프로필 조회 : RequestHeader(userUuid), RequestParamter(profileType)
    ProfileEntity findByUserUuidAndProfileType(@Param("userUuid") String userUuid, @Param("profileType") ProfileType profileType);

//    @Query("select new com.goorm.devlink.profileservice.dto.ProfileDto(p.profileUuid, p.userUuid, p.profileImageUrl, p.name, p.nickname, p.introduction, p.stacks, p.address) " +
//            "from ProfileEntity p where p.profileUuid = :profileUuid")
//    ProfileDto findByProfileUuid(@Param("profileUuid") String profileUuid);
//
//    @Query("select new com.goorm.devlink.profileservice.dto.ProfileDto(p.profileUuid, p.userUuid, p.profileImageUrl, p.name, p.nickname, p.introduction, p.stacks, p.address) " +
//            "from ProfileEntity p where p.profileUuid = :profileUuid and p.userUuid = :userUuid")
//    ProfileDto findByProfileAndUserUuid(@Param("profileUuid") String profileUuid, @Param("userUuid") String userUuid);

//    @Query("select new com.goorm.devlink.profileservice.dto.ProfileDto(p.id, p.profileUuid, p.profileImageUrl, p.nickname, p.stacks, p.address) " +
//            "from ProfileEntity p where (p.stacks like %:keyword% or p.address like %:keyword%)")
//    List<ProfileDto> findProfilesByStackKeyword(@Param("keyword") String keyword);
//
//    @Transactional
//    @Modifying(clearAutomatically = true)
//    @Query("delete from ProfileEntity p where (p.profileUuid = :profileUuid and p.userUuid = :userUuid)")
//    void deleteByProfileAndUserUuid(@Param("profileUuid") String profileUuid, @Param("userUuid") String userUuid);
}
