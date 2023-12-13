package com.goorm.devlink.profileservice.util;

import com.goorm.devlink.profileservice.dto.ProfileDto;
import com.goorm.devlink.profileservice.entity.ProfileEntity;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;

import java.util.ArrayList;
import java.util.List;

@RequiredArgsConstructor
public class ModelMapperUtil {

    private final ModelMapper mapper;

    public ProfileEntity convertToProfileEntity(ProfileDto profileDto) {
        mapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);
        ProfileEntity profileEntity = mapper.map(profileDto, ProfileEntity.class);
        return profileEntity;
    }

    public ProfileDto convertToProfileDto(ProfileEntity profileEntity) {
        mapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);
        ProfileDto profileDto = mapper.map(profileEntity, ProfileDto.class);
        return profileDto;
    }

    public List<ProfileDto> convertToProfileDtoList(List<ProfileEntity> profileEntityList) {
        List<ProfileDto> retProfileDtoList = new ArrayList<>();
        for (ProfileEntity profileEntity : profileEntityList) {
             retProfileDtoList.add(convertToProfileDto(profileEntity));
        }
        return retProfileDtoList;
    }
}
