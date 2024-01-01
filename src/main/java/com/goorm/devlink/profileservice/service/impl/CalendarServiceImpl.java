package com.goorm.devlink.profileservice.service.impl;

import com.goorm.devlink.profileservice.dto.ScheduleDto;
import com.goorm.devlink.profileservice.entity.CalendarEntity;
import com.goorm.devlink.profileservice.entity.ProfileEntity;
import com.goorm.devlink.profileservice.entity.ScheduleEntity;
import com.goorm.devlink.profileservice.repository.CalendarRepository;
import com.goorm.devlink.profileservice.repository.ProfileRepository;
import com.goorm.devlink.profileservice.service.CalendarService;
import com.goorm.devlink.profileservice.service.ScheduleService;
import com.goorm.devlink.profileservice.util.MessageUtil;
import com.goorm.devlink.profileservice.util.ModelMapperUtil;
import com.goorm.devlink.profileservice.vo.request.ScheduleCreateRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class CalendarServiceImpl implements CalendarService {

    private final ProfileRepository profileRepository;
    private final CalendarRepository calendarRepository;
    private final ScheduleService scheduleService;
    private final ModelMapperUtil modelMapperUtil;
    private final MessageUtil messageUtil;

    @Transactional(readOnly = true)
    @Override
    public List<ScheduleDto> getCalendarScheduleDtos(String userUuid) {

        ProfileEntity profileEntity = profileRepository.findByUserUuid(userUuid);
        CalendarEntity calendarEntity = calendarRepository.findByProfileEntity(profileEntity);
        List<ScheduleEntity> scheduleEntities = scheduleService.getScheduleEntitiesByCalenderEntity(calendarEntity);
        List<ScheduleDto> scheduleDtos = modelMapperUtil.mapToScheduleDtoList(scheduleEntities);
        return scheduleDtos;
    }

    @Transactional
    @Override
    public void saveCalendarByScheduleCreateRequest(String userUuid, ScheduleCreateRequest scheduleCreateRequest) {

        ProfileEntity profileEntity = profileRepository.findByUserUuid(userUuid);
        CalendarEntity calendarEntity = calendarRepository.findByProfileEntity(profileEntity);
        scheduleService.saveScheduleByCalendarAndCreateRequest(calendarEntity, scheduleCreateRequest);
    }

    @Transactional
    @Override
    public void deleteScheduleByUserUuidAndMentoringUuid(String userUuid, String mentoringUuid) {
        ProfileEntity profileEntity = profileRepository.findByUserUuid(userUuid);
        CalendarEntity calendarEntity = calendarRepository.findByProfileEntity(profileEntity);
        try {
            scheduleService.deleteScheduleByCalendarEntityAndMentoringUuid(calendarEntity, mentoringUuid);
        } catch (Exception e) {
            throw new RuntimeException(messageUtil.getScheduleCancelErrorMessage());
        }
    }

    @Transactional(readOnly = true)
    @Override
    public List<String> findEnableUserUuidListByValidCalendar(List<String> userUuidList, LocalDateTime startTime, int unitTimeCount) {
        List<String> enableUserUuidList = new ArrayList<>();
        List<ProfileEntity> profileEntityList = profileRepository.findByUserUuidIn(userUuidList);
        List<CalendarEntity> calendarEntityList = calendarRepository.findByProfileEntityIn(profileEntityList);
        List<CalendarEntity> validCalendarEntityList = scheduleService.getValidCalendarEntityList(calendarEntityList, startTime, unitTimeCount);
        validCalendarEntityList.iterator().forEachRemaining(calendarEntity ->
                enableUserUuidList.add(calendarEntity.getProfileEntity().getUserUuid()));
        return enableUserUuidList;
    }
}
