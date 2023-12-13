package com.goorm.devlink.profileservice.service.impl;

import com.goorm.devlink.profileservice.dto.ProfileDto;
import com.goorm.devlink.profileservice.entity.ProfileEntity;
import com.goorm.devlink.profileservice.repository.ProfileRepository;
import com.goorm.devlink.profileservice.service.ProfileService;
import com.goorm.devlink.profileservice.util.ModelMapperUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ProfileServiceImpl implements ProfileService {

    private final ProfileRepository profileRepository;
    private final ModelMapperUtil modelMapperUtil;

    @Override
    public String createProfile(ProfileDto profileDto) {
        ProfileEntity profileEntity = modelMapperUtil.convertToProfileEntity(profileDto);
        profileRepository.save(profileEntity);
        return profileEntity.getProfileUuid();
    }


}
