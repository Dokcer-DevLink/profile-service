package com.goorm.devlink.profileservice.util;

import com.goorm.devlink.profileservice.dto.ProfileDto;
import com.goorm.devlink.profileservice.entity.ProfileEntity;
import com.goorm.devlink.profileservice.vo.ProfileSimpleCardResponse;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.data.domain.Slice;
import org.springframework.data.domain.SliceImpl;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

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

    public ProfileSimpleCardResponse convertToProfileSimpleCardResponse(ProfileEntity profileEntity) {
        mapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);
//        ProfileSimpleCardResponse profileSimpleCardResponse = mapper.map(profileEntity, ProfileSimpleCardResponse.class);
        ProfileSimpleCardResponse profileSimpleCardResponse = new ProfileSimpleCardResponse(
                profileEntity.getUserUuid(), profileEntity.getProfileImageUrl(), profileEntity.getNickname(), profileEntity.getAddress(), profileEntity.getStacks());
        return profileSimpleCardResponse;
    }

    public List<ProfileDto> convertToProfileDtoList(List<ProfileEntity> profileEntityList) {
        List<ProfileDto> retProfileDtoList = new ArrayList<>();
        for (ProfileEntity profileEntity : profileEntityList) {
             retProfileDtoList.add(convertToProfileDto(profileEntity));
        }
        return retProfileDtoList;
    }

    public Slice<ProfileDto> mapToProfileDtoSlice(Slice<ProfileEntity> profileEntitySlice) {
        List<ProfileDto> profileDtoList = profileEntitySlice.getContent().stream()
                .map(this::convertToProfileDto)
                .collect(Collectors.toList());

        return new SliceImpl<>(profileDtoList, profileEntitySlice.getPageable(), profileEntitySlice.hasNext());
    }

    public Slice<ProfileSimpleCardResponse> mapToProfileSimpleCardResponse(Slice<ProfileEntity> profileEntitySlice) {
        List<ProfileSimpleCardResponse> profileSimpleCardResponseList = profileEntitySlice.getContent().stream()
                .map(this::convertToProfileSimpleCardResponse)
                .collect(Collectors.toList());

        return new SliceImpl<>(profileSimpleCardResponseList, profileEntitySlice.getPageable(), profileEntitySlice.hasNext());
    }
}
