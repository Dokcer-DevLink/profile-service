package com.goorm.devlink.profileservice.service;

import com.goorm.devlink.profileservice.dto.ProfileDto;
import com.goorm.devlink.profileservice.entity.constant.ProfileType;
import com.goorm.devlink.profileservice.vo.request.ProfileEditRequest;
import com.goorm.devlink.profileservice.vo.response.ProfileSimpleCardResponse;
import org.springframework.data.domain.Slice;

public interface ProfileService {

    void createProfile(ProfileDto profileDto);

    void updateProfile(ProfileEditRequest profileEditRequest, String userUuid, String profileImageUrl);

    void updateProfileWithoutImageUrl(ProfileEditRequest profileEditRequest, String userUuid);

    ProfileDto getMyProfile(String userUuid);

    ProfileDto getProfileByUserUuid(String userUuid);

    Slice<ProfileSimpleCardResponse> getSimpleCardSliceByTypeAndKeyword(ProfileType profileType, String keyword, int pageNumber);

    void deleteProfileByUserUuid(String userUuid);
}
