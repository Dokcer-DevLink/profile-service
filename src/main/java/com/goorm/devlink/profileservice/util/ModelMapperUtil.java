package com.goorm.devlink.profileservice.util;

import com.goorm.devlink.profileservice.dto.ProfileDto;
import com.goorm.devlink.profileservice.entity.ProfileEntity;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;

@RequiredArgsConstructor
public class ModelMapperUtil {

    private final ModelMapper mapper;

    public ProfileEntity convertToProfileEntity(ProfileDto profileDto) {
        mapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);
        ProfileEntity profileEntity = mapper.map(profileDto, ProfileEntity.class);
        return profileEntity;
    }
}
