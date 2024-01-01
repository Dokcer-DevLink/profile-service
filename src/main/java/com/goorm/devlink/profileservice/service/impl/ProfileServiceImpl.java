package com.goorm.devlink.profileservice.service.impl;

import com.goorm.devlink.profileservice.dto.ProfileDto;
import com.goorm.devlink.profileservice.entity.CalendarEntity;
import com.goorm.devlink.profileservice.entity.ProfileEntity;
import com.goorm.devlink.profileservice.entity.constant.ProfileType;
import com.goorm.devlink.profileservice.repository.CalendarRepository;
import com.goorm.devlink.profileservice.repository.ProfileRepository;
import com.goorm.devlink.profileservice.service.ProfileService;
import com.goorm.devlink.profileservice.service.S3UploadService;
import com.goorm.devlink.profileservice.util.MessageUtil;
import com.goorm.devlink.profileservice.util.ModelMapperUtil;
import com.goorm.devlink.profileservice.vo.request.ProfileEditRequest;
import com.goorm.devlink.profileservice.vo.ProfileSimpleCard;
import com.goorm.devlink.profileservice.vo.response.ProfileSimpleResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ProfileServiceImpl implements ProfileService {

    private final ProfileRepository profileRepository;
    private final CalendarRepository calendarRepository;
    private final ModelMapperUtil modelMapperUtil;
    private final MessageUtil messageUtil;

    private final S3UploadService s3UploadService;

    @Transactional
    @Override
    public void createProfile(ProfileDto profileDto) {
        ProfileEntity profileEntity = modelMapperUtil.convertToProfileEntity(profileDto);
        profileEntity.setProfileType(ProfileType.BOTH);
        try {
            profileRepository.save(profileEntity);

            CalendarEntity calendarEntity = new CalendarEntity();
            calendarEntity.setProfileEntity(profileEntity);
            try {
                calendarRepository.save(calendarEntity);
            } catch (Exception e) {
                throw new RuntimeException(messageUtil.getCalendarCreateErrorMessage());
            }
        } catch (Exception e) {
            throw new RuntimeException(messageUtil.getProfileCreateErrorMessage());
        }
    }

    @Transactional
    @Override
    public ProfileDto updateProfile(ProfileEditRequest profileEditRequest, String userUuid, String profileImageUrl) {
        ProfileEntity profileEntity = Optional.ofNullable(profileRepository.findByUserUuid(userUuid)).orElseThrow(() -> {
            throw new NoSuchElementException(messageUtil.getUserUuidNoSuchMessage(userUuid)); });

        try {
            profileEntity.setProfileImageUrl(profileImageUrl);
            profileEntity.setNickname(profileEditRequest.getNickname());
            profileEntity.setGithubAddress(profileEditRequest.getGithubAddress());
            profileEntity.setIntroduction(profileEditRequest.getIntroduction());
            profileEntity.setProfileType(profileEditRequest.getProfileType());
            profileEntity.setAddress(profileEditRequest.getAddress());
            profileEntity.setStacks(profileEditRequest.getStacks());

            ProfileEntity profile = profileRepository.save(profileEntity);
            ProfileDto profileDto = modelMapperUtil.convertToProfileDto(profileEntity);
            return profileDto;
        } catch (Exception e) {
            throw new RuntimeException(messageUtil.getProfileUpdateErrorMessage());
        }
    }

    @Override
    @Transactional
    public ProfileDto newUpdateProfile(ProfileEditRequest request, String userUuid) {
        ProfileEntity profileEntity = Optional.ofNullable(profileRepository.findByUserUuid(userUuid)).orElseThrow(() -> {
            throw new NoSuchElementException(messageUtil.getUserUuidNoSuchMessage(userUuid)); });

        if (request.getFileData() == null) {
            // 이미지 삭제
            profileEntity.setProfileImageUrl(null);
        } else {
            if (profileEntity.getProfileImageUrl() == null) {
                // 이미지 업로드
                String profileImageUrl = s3UploadService.saveFile(request.getFileData(), userUuid);
                profileEntity.setProfileImageUrl(profileImageUrl);
            }
        }

        profileEntity.setNickname(request.getNickname());
        profileEntity.setGithubAddress(request.getGithubAddress());
        profileEntity.setIntroduction(request.getIntroduction());
        profileEntity.setProfileType(request.getProfileType());
        profileEntity.setAddress(request.getAddress());
        profileEntity.setStacks(request.getStacks());

        profileRepository.save(profileEntity);
        return modelMapperUtil.convertToProfileDto(profileEntity);
    }

    @Transactional
    @Override
    public ProfileDto updateProfileWithoutImageUrl(ProfileEditRequest profileEditRequest, String userUuid) {
        ProfileEntity profileEntity = Optional.ofNullable(profileRepository.findByUserUuid(userUuid)).orElseThrow(() -> {
            throw new NoSuchElementException(messageUtil.getUserUuidNoSuchMessage(userUuid)); });

        profileEntity.setProfileImageUrl(profileEntity.getProfileImageUrl());
        profileEntity.setNickname(profileEditRequest.getNickname());
        profileEntity.setIntroduction(profileEditRequest.getIntroduction());
        profileEntity.setProfileType(profileEditRequest.getProfileType());
        profileEntity.setAddress(profileEditRequest.getAddress());
        profileEntity.setStacks(profileEditRequest.getStacks());

        ProfileEntity profile = profileRepository.save(profileEntity);
        return modelMapperUtil.convertToProfileDto(profileEntity);
    }

    @Transactional(readOnly = true)
    @Override
    public ProfileDto getMyProfile(String userUuid) {
        ProfileEntity profileEntity = Optional.ofNullable(profileRepository.findByUserUuid(userUuid)).orElseThrow(() -> {
            throw new NoSuchElementException(messageUtil.getUserUuidNoSuchMessage(userUuid)); });
        ProfileDto profileDto = modelMapperUtil.convertToProfileDto(profileEntity);
        return profileDto;
    }

    @Transactional(readOnly = true)
    @Override
    public ProfileDto getProfileByUserUuid(String userUuid) {
        ProfileEntity profileEntity = Optional.ofNullable(profileRepository.findByUserUuid(userUuid)).orElseThrow(() -> {
            throw new NoSuchElementException(messageUtil.getUserUuidNoSuchMessage(userUuid)); });
        ProfileDto profileDto = modelMapperUtil.convertToProfileDto(profileEntity);
        return profileDto;
    }

    @Transactional(readOnly = true)
    @Override
    public List<ProfileSimpleResponse> getProfileByUserUuidList(List<String> userUuidList) {
        List<ProfileEntity> profileEntityList = Optional.ofNullable(profileRepository.findByUserUuidIn(userUuidList)).orElseThrow(() -> {
            throw new NoSuchElementException(messageUtil.getUserUuidNoSuchMessage(userUuidList.toString())); });
        List<ProfileSimpleResponse> profileSimpleResponseList = modelMapperUtil.mapProfileEntityListToProfileSimpleResponseList(profileEntityList);
        return profileSimpleResponseList;
    }

    @Transactional(readOnly = true)
    @Override
    public Slice<ProfileSimpleCard> getSimpleCardSliceByTypeAndKeyword(ProfileType profileType, String keyword, Pageable pageable) {
        Slice<ProfileEntity> profileEntitySlice = profileRepository.findSliceByStackKeyword(profileType, keyword, pageable);
        Slice<ProfileSimpleCard> profileSimpleCardSlice = modelMapperUtil.mapProfileEntitySliceToProfileSimpleCard(profileEntitySlice);
        return profileSimpleCardSlice;
    }

    @Transactional(readOnly = true)
    @Override
    public List<ProfileSimpleCard> getSimpleCardListForRecommend(ProfileType profileType) {
        List<ProfileEntity> profileEntityList = profileRepository.findListByProfileType(profileType, PageRequest.of(0, 8));
        List<ProfileSimpleCard> profileSimpleCardList = modelMapperUtil.mapProfileEntityListToProfileSimpleCard(profileEntityList);
        return profileSimpleCardList;
    }

    @Transactional(readOnly = true)
    @Override
    public List<ProfileSimpleCard> getSimpleCardListByUserUuidList(List<String> userUuidList) {
        List<ProfileEntity> profileEntityList = profileRepository.findByUserUuidIn(userUuidList);
        List<ProfileSimpleCard> profileSimpleCardList = modelMapperUtil.mapProfileEntityListToProfileSimpleCard(profileEntityList);
        return profileSimpleCardList;
    }

    @Transactional
    @Override
    public void deleteProfileByUserUuid(String userUuid) {
        try {
            profileRepository.deleteProfileByUserUuid(userUuid);
        } catch (Exception e) {
            throw new RuntimeException(messageUtil.getProfileDeleteMessage());
        }
    }
}
