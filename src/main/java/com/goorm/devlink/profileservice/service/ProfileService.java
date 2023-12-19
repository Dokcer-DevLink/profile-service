package com.goorm.devlink.profileservice.service;

import com.goorm.devlink.profileservice.dto.ProfileDto;
import com.goorm.devlink.profileservice.entity.ProfileEntity;
import com.goorm.devlink.profileservice.entity.ProfileType;
import com.goorm.devlink.profileservice.vo.ProfileSimpleCardResponse;
import org.springframework.data.domain.Slice;

import java.util.List;

public interface ProfileService {

    void testMethod();

    String createProfile(ProfileDto profileDto);
    ProfileDto getMyProfile(String userUuid, ProfileType profileType);

    ProfileDto getProfileByUserUuidAndProfileUuid(String userUuid, String profileUuid);

    List<ProfileDto> getProfileListByTypeAndKeyword(ProfileType profileType, String keyword);

    Slice<ProfileDto> getSliceByTypeAndKeyword(ProfileType profileType, String keyword, int pageNumber);

    Slice<ProfileSimpleCardResponse> getSimpleCardSliceByTypeAndKeyword(ProfileType profileType, String keyword, int pageNumber);

    void deleteProfileByUserAndProfileUuid(String userUuid, String profileUuid);
}
