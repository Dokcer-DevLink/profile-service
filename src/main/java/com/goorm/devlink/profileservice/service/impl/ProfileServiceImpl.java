package com.goorm.devlink.profileservice.service.impl;

import com.goorm.devlink.profileservice.dto.ProfileDto;
import com.goorm.devlink.profileservice.entity.ProfileEntity;
import com.goorm.devlink.profileservice.entity.ProfileType;
import com.goorm.devlink.profileservice.repository.ProfileRepository;
import com.goorm.devlink.profileservice.service.ProfileService;
import com.goorm.devlink.profileservice.util.ModelMapperUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ProfileServiceImpl implements ProfileService {

    private final ProfileRepository profileRepository;
    private final ModelMapperUtil modelMapperUtil;

    @Override
    public void testMethod() {
        List<String> stacks = new ArrayList<>();
        stacks.add("python");
        stacks.add("django");
        stacks.add("docker");
        ProfileEntity testEntity = ProfileEntity.getInstanceTest(1, ProfileType.MENTOR, "useruuid1", stacks);
        profileRepository.save(testEntity);
    }

    @Override
    public String createProfile(ProfileDto profileDto) {
        ProfileEntity profileEntity = modelMapperUtil.convertToProfileEntity(profileDto);
        profileRepository.save(profileEntity);
        return profileEntity.getProfileUuid();
    }

    @Override
    public ProfileDto getMyProfile(String userUuid, ProfileType profileType) {
        ProfileEntity profileEntity = profileRepository.findByUserUuidAndProfileType(userUuid, profileType);
        ProfileDto profileDto = modelMapperUtil.convertToProfileDto(profileEntity);
        return profileDto;
    }

    @Override
    public ProfileDto getProfileByUserUuidAndProfileUuid(String userUuid, String profileUuid) {
        ProfileEntity profileEntity = profileRepository.findByUserUuidAndProfileUuid(userUuid, profileUuid);
        ProfileDto profileDto = modelMapperUtil.convertToProfileDto(profileEntity);
        return profileDto;
    }

    @Override
    public List<ProfileDto> getProfileListByTypeAndKeyword(ProfileType profileType, String keyword) {
//        List<ProfileEntity> profileEntityList = profileRepository.findProfilesByKeyword(keyword);
        List<ProfileEntity> profileEntityList = profileRepository.findProfileListByStackKeyword(profileType, keyword);
        List<ProfileDto> profileDtoList = modelMapperUtil.convertToProfileDtoList(profileEntityList);
//        List<ProfileDto> profileDtoList = profileRepository.findProfilesByKeyword(keyword);
        return profileDtoList;
    }

    @Override
    public void deleteProfileByUserAndProfileUuid(String userUuid, String profileUuid) {
//        profileRepository.deleteProfileByUserUuidAndProfileUuid(userUuid, profileUuid);
        profileRepository.deleteProfileByProfileUuid(profileUuid);
    }

//    @Override
//    public ProfileDto getProfileByProfileAndUserUuid(String profileUuid, String userUuid) {
//        ProfileDto byUuid = profileRepository.findByProfileAndUserUuid(profileUuid, userUuid);
//        return byUuid;
//    }
}
