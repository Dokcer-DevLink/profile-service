package com.goorm.devlink.profileservice.service.impl;

import com.goorm.devlink.profileservice.dto.ProfileDto;
import com.goorm.devlink.profileservice.entity.CalendarEntity;
import com.goorm.devlink.profileservice.entity.ProfileEntity;
import com.goorm.devlink.profileservice.entity.constant.ProfileType;
import com.goorm.devlink.profileservice.repository.CalendarRepository;
import com.goorm.devlink.profileservice.repository.ProfileRepository;
import com.goorm.devlink.profileservice.service.ProfileService;
import com.goorm.devlink.profileservice.util.ModelMapperUtil;
import com.goorm.devlink.profileservice.vo.request.ProfileEditRequest;
import com.goorm.devlink.profileservice.vo.ProfileSimpleCard;
import com.goorm.devlink.profileservice.vo.response.ProfileSimpleCardListResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Slice;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class ProfileServiceImpl implements ProfileService {

    private final ProfileRepository profileRepository;
    private final CalendarRepository calendarRepository;
    private final ModelMapperUtil modelMapperUtil;

    @Transactional
    @Override
    public void createProfile(ProfileDto profileDto) {
        ProfileEntity profileEntity = modelMapperUtil.convertToProfileEntity(profileDto);
        try {
            profileRepository.save(profileEntity);

            CalendarEntity calendarEntity = new CalendarEntity();
            calendarEntity.setProfileEntity(profileEntity);
            try {
                calendarRepository.save(calendarEntity);
            } catch (Exception e) {
                throw new RuntimeException("Calendar creation error.");
            }
        } catch (Exception e) {
            throw new RuntimeException("Profile creation error.");
        }
    }

    @Transactional
    @Override
    public void updateProfile(ProfileEditRequest profileEditRequest, String userUuid, String profileImageUrl) {
        ProfileEntity profileEntity = profileRepository.findByUserUuid(userUuid);

        profileEntity.setProfileImageUrl(profileImageUrl);
        profileEntity.setNickname(profileEditRequest.getNickname());
        profileEntity.setGithubAddress(profileEditRequest.getGithubAddress());
        profileEntity.setIntroduction(profileEditRequest.getIntroduction());
        profileEntity.setProfileType(profileEditRequest.getProfileType());
        profileEntity.setAddress(profileEditRequest.getAddress());
        profileEntity.setStacks(profileEditRequest.getStacks());

        profileRepository.save(profileEntity);
    }

    @Transactional
    @Override
    public void updateProfileWithoutImageUrl(ProfileEditRequest profileEditRequest, String userUuid) {
        ProfileEntity profileEntity = profileRepository.findByUserUuid(userUuid);

        profileEntity.setProfileImageUrl(profileEntity.getProfileImageUrl());
        profileEntity.setNickname(profileEditRequest.getNickname());
        profileEntity.setIntroduction(profileEditRequest.getIntroduction());
        profileEntity.setProfileType(profileEditRequest.getProfileType());
        profileEntity.setAddress(profileEditRequest.getAddress());
        profileEntity.setStacks(profileEditRequest.getStacks());

        profileRepository.save(profileEntity);
    }

    @Transactional(readOnly = true)
    @Override
    public ProfileDto getMyProfile(String userUuid) {
        ProfileEntity profileEntity = profileRepository.findByUserUuid(userUuid);
        ProfileDto profileDto = modelMapperUtil.convertToProfileDto(profileEntity);
        return profileDto;
    }

    @Transactional(readOnly = true)
    @Override
    public ProfileDto getProfileByUserUuid(String userUuid) {
        ProfileEntity profileEntity = profileRepository.findByUserUuid(userUuid);
        ProfileDto profileDto = modelMapperUtil.convertToProfileDto(profileEntity);
        return profileDto;
    }

    @Transactional(readOnly = true)
    @Override
    public Slice<ProfileSimpleCard> getSimpleCardSliceByTypeAndKeyword(ProfileType profileType, String keyword, int pageNumber) {
        Slice<ProfileEntity> profileEntitySlice = profileRepository.findSliceByStackKeyword(profileType, keyword, PageRequest.of(pageNumber, 8));
        Slice<ProfileSimpleCard> profileSimpleCardSlice = modelMapperUtil.mapToProfileSimpleCard(profileEntitySlice);
        return profileSimpleCardSlice;
    }

    @Transactional
    @Override
    public void deleteProfileByUserUuid(String userUuid) {
        try {
            profileRepository.deleteProfileByUserUuid(userUuid);
        } catch (Exception e) {
            throw new RuntimeException("Profile delete error.");
        }
    }
}
