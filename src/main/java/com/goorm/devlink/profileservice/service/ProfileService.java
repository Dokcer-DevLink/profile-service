package com.goorm.devlink.profileservice.service;

import com.goorm.devlink.profileservice.dto.ProfileDto;
import com.goorm.devlink.profileservice.entity.ProfileEntity;
import com.goorm.devlink.profileservice.entity.ProfileType;
import com.goorm.devlink.profileservice.vo.ProfileSimpleCardResponse;
import org.springframework.data.domain.Slice;

import java.util.List;

public interface ProfileService {

    void testMethod();

    void createProfile(ProfileDto profileDto);
    ProfileDto getMyProfile(String userUuid, ProfileType profileType);

    ProfileDto getProfileByUserUuid(String userUuid);

    List<ProfileDto> getProfileListByTypeAndKeyword(ProfileType profileType, String keyword);

    Slice<ProfileDto> getSliceByTypeAndKeyword(ProfileType profileType, String keyword, int pageNumber);

    Slice<ProfileSimpleCardResponse> getSimpleCardSliceByTypeAndKeyword(ProfileType profileType, String keyword, int pageNumber);

    void deleteProfileByUserUuid(String userUuid);
}
