package com.goorm.devlink.profileservice.service;

import com.goorm.devlink.profileservice.dto.ProfileDto;
import com.goorm.devlink.profileservice.entity.ProfileType;

import java.util.List;

public interface ProfileService {

    void testMethod();

    String createProfile(ProfileDto profileDto);
    ProfileDto getMyProfile(String userUuid, ProfileType profileType);

    ProfileDto getProfileByUserUuidAndProfileUuid(String userUuid, String profileUuid);

    List<ProfileDto> getProfileListByTypeAndKeyword(ProfileType profileType, String keyword);

    public void deleteProfileByUserAndProfileUuid(String userUuid, String profileUuid);
}
