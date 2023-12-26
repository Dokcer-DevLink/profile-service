package com.goorm.devlink.profileservice.util;

import com.goorm.devlink.profileservice.dto.CalendarDto;
import com.goorm.devlink.profileservice.dto.ProfileDto;
import com.goorm.devlink.profileservice.dto.ScheduleDto;
import com.goorm.devlink.profileservice.entity.CalendarEntity;
import com.goorm.devlink.profileservice.entity.ProfileEntity;
import com.goorm.devlink.profileservice.entity.ScheduleEntity;
import com.goorm.devlink.profileservice.vo.response.ProfileSimpleCardResponse;
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

    public CalendarDto convertToCalendarDto(CalendarEntity calendarEntity) {
        mapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);
        CalendarDto calendarDto = mapper.map(calendarEntity, CalendarDto.class);
        return calendarDto;
    }

    public CalendarEntity convertToCalendarEntity(CalendarDto calendarDto) {
        mapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);
        CalendarEntity calendarEntity = mapper.map(calendarDto, CalendarEntity.class);
        return calendarEntity;
    }

    public ScheduleDto convertToScheduleDto(ScheduleEntity scheduleEntity) {
        mapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);
        ScheduleDto scheduleDto = mapper.map(scheduleEntity, ScheduleDto.class);
        return scheduleDto;
    }

    public ScheduleEntity convertToScheduleEntity(ScheduleDto scheduleDto) {
        mapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);
        ScheduleEntity scheduleEntity = mapper.map(scheduleDto, ScheduleEntity.class);
        return scheduleEntity;
    }

    public List<ScheduleDto> mapToScheduleDtoList(List<ScheduleEntity> scheduleEntityList) {
        List<ScheduleDto> retScheduleDtoList = new ArrayList<>();
        for (ScheduleEntity scheduleEntity : scheduleEntityList) {
            retScheduleDtoList.add(convertToScheduleDto(scheduleEntity));
        }
        return retScheduleDtoList;
    }
}
