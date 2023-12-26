package com.goorm.devlink.profileservice.service.impl;

import com.goorm.devlink.profileservice.dto.ProfileDto;
import com.goorm.devlink.profileservice.entity.ProfileEntity;
import com.goorm.devlink.profileservice.entity.ProfileType;
import com.goorm.devlink.profileservice.repository.ProfileRepository;
import com.goorm.devlink.profileservice.service.ProfileService;
import com.goorm.devlink.profileservice.util.ModelMapperUtil;
import com.goorm.devlink.profileservice.vo.request.ProfileEditRequest;
import com.goorm.devlink.profileservice.vo.response.ProfileSimpleCardResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Slice;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ProfileServiceImpl implements ProfileService {

    private final ProfileRepository profileRepository;
    private final ModelMapperUtil modelMapperUtil;

    @Transactional
    @Override
    public void testMethod() {

        List<String> stackTemplateList = new ArrayList<>();
        stackTemplateList.add("java");
        stackTemplateList.add("spring");
        stackTemplateList.add("jpa");
        stackTemplateList.add("python");
        stackTemplateList.add("django");
        stackTemplateList.add("flask");
        stackTemplateList.add("javascript");
        stackTemplateList.add("react");
        stackTemplateList.add("nodejs");
        stackTemplateList.add("git");
        stackTemplateList.add("docker");
        stackTemplateList.add("kubernetes");
        stackTemplateList.add("jenkins");
        stackTemplateList.add("argocd");
        stackTemplateList.add("terraform");
        stackTemplateList.add("helm");
        stackTemplateList.add("aws");
        stackTemplateList.add("typescript");
        stackTemplateList.add("kotlin");
        stackTemplateList.add("kafka");

        for (int i = 0; i < 100; i++) {
            List<String> stacks = new ArrayList<>();
            stacks.add(stackTemplateList.get(i%18));
            stacks.add(stackTemplateList.get(i%18+1));
            stacks.add(stackTemplateList.get(i%18+2));
            if (i % 3 == 0) {
                ProfileEntity testEntity = ProfileEntity.getInstanceTest(i, ProfileType.MENTOR, "useruuid" + i, stacks);
                profileRepository.save(testEntity);
            } else if (i % 3 == 1) {
                ProfileEntity testEntity = ProfileEntity.getInstanceTest(i, ProfileType.MENTEE, "useruuid" + i, stacks);
                profileRepository.save(testEntity);
            } else if (i % 3 == 2) {
                ProfileEntity testEntity = ProfileEntity.getInstanceTest(i, ProfileType.BOTH, "useruuid" + i, stacks);
                profileRepository.save(testEntity);
            } else {
                ProfileEntity testEntity = ProfileEntity.getInstanceTest(i, ProfileType.NULL, "useruuid" + i, stacks);
                profileRepository.save(testEntity);
            }
        }
    }

    @Transactional
    @Override
    public void createProfile(ProfileDto profileDto) {
        ProfileEntity profileEntity = modelMapperUtil.convertToProfileEntity(profileDto);
        try {
            profileRepository.save(profileEntity);
        } catch (Exception e) {
            throw new RuntimeException("Profile creation error.");
        }
    }

    @Transactional
    @Override
    public void updateProfile(ProfileEditRequest profileEditRequest, String userUuid, String profileImageUrl) {
        ProfileEntity profileEntity = profileRepository.findByUserUuid(userUuid);

        profileEntity.setProfileImageUrl(profileImageUrl);
        profileEntity.setName(profileEditRequest.getName());
        profileEntity.setNickname(profileEditRequest.getNickname());
        profileEntity.setIntroduction(profileEditRequest.getIntroduction());
        profileEntity.setCareer(profileEditRequest.getCareer());
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
        profileEntity.setName(profileEditRequest.getName());
        profileEntity.setNickname(profileEditRequest.getNickname());
        profileEntity.setIntroduction(profileEditRequest.getIntroduction());
        profileEntity.setCareer(profileEditRequest.getCareer());
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
    public Slice<ProfileSimpleCardResponse> getSimpleCardSliceByTypeAndKeyword(ProfileType profileType, String keyword, int pageNumber) {
        Slice<ProfileEntity> profileEntitySlice = profileRepository.findSliceByStackKeyword(profileType, keyword, PageRequest.of(pageNumber, 8));
        Slice<ProfileSimpleCardResponse> profileSimpleCardResponseSlice = modelMapperUtil.mapToProfileSimpleCardResponse(profileEntitySlice);
        return profileSimpleCardResponseSlice;
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
