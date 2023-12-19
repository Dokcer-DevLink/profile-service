package com.goorm.devlink.profileservice.service.impl;

import com.goorm.devlink.profileservice.dto.ProfileDto;
import com.goorm.devlink.profileservice.entity.ProfileEntity;
import com.goorm.devlink.profileservice.entity.ProfileType;
import com.goorm.devlink.profileservice.repository.ProfileRepository;
import com.goorm.devlink.profileservice.service.ProfileService;
import com.goorm.devlink.profileservice.util.ModelMapperUtil;
import com.goorm.devlink.profileservice.vo.ProfileSimpleCardResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
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
            ProfileEntity testEntity = ProfileEntity.getInstanceTest(i, (i%2==0 ? ProfileType.MENTOR:ProfileType.MENTEE), "useruuid"+i, stacks);
            profileRepository.save(testEntity);
        }
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
    public Slice<ProfileDto> getSliceByTypeAndKeyword(ProfileType profileType, String keyword, int pageNumber) {
        Slice<ProfileEntity> profileEntitySlice = profileRepository.findSliceByStackKeyword(profileType, keyword, PageRequest.of(pageNumber, 8));
        Slice<ProfileDto> profileDtoSlice = modelMapperUtil.mapToProfileDtoSlice(profileEntitySlice);
        return profileDtoSlice;
    }

    @Override
    public Slice<ProfileSimpleCardResponse> getSimpleCardSliceByTypeAndKeyword(ProfileType profileType, String keyword, int pageNumber) {
        Slice<ProfileEntity> profileEntitySlice = profileRepository.findSliceByStackKeyword(profileType, keyword, PageRequest.of(pageNumber, 8));
        Slice<ProfileSimpleCardResponse> profileSimpleCardResponseSlice = modelMapperUtil.mapToProfileSimpleCardResponse(profileEntitySlice);
        return profileSimpleCardResponseSlice;
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
