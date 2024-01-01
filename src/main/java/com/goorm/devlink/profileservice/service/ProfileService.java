package com.goorm.devlink.profileservice.service;

import com.goorm.devlink.profileservice.dto.ProfileDto;
import com.goorm.devlink.profileservice.entity.constant.ProfileType;
import com.goorm.devlink.profileservice.vo.request.ProfileEditRequest;
import com.goorm.devlink.profileservice.vo.ProfileSimpleCard;
import com.goorm.devlink.profileservice.vo.response.ProfileSimpleResponse;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;

import java.util.List;

public interface ProfileService {

    void createProfile(ProfileDto profileDto);

    ProfileDto updateProfile(ProfileEditRequest profileEditRequest, String userUuid, String profileImageUrl);

    ProfileDto updateProfileWithoutImageUrl(ProfileEditRequest profileEditRequest, String userUuid);

    ProfileDto getMyProfile(String userUuid);

    ProfileDto getProfileByUserUuid(String userUuid);

    List<ProfileSimpleResponse> getProfileByUserUuidList(List<String> userUuidList);

    Slice<ProfileSimpleCard> getSimpleCardSliceByTypeAndKeyword(ProfileType profileType, String keyword, Pageable pageable);

    List<ProfileSimpleCard> getSimpleCardListForRecommend(ProfileType profileType);

    List<ProfileSimpleCard> getSimpleCardListByUserUuidList(List<String> userUuidList);

    void deleteProfileByUserUuid(String userUuid);

    ProfileDto newUpdateProfile(ProfileEditRequest profileEditRequest, String userUuid);
}
