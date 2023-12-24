package com.goorm.devlink.profileservice.service.impl;

import com.goorm.devlink.profileservice.dto.CalendarDto;
import com.goorm.devlink.profileservice.entity.CalendarEntity;
import com.goorm.devlink.profileservice.entity.ProfileEntity;
import com.goorm.devlink.profileservice.repository.CalendarRepository;
import com.goorm.devlink.profileservice.repository.ProfileRepository;
import com.goorm.devlink.profileservice.repository.ScheduleRepository;
import com.goorm.devlink.profileservice.service.CalendarService;
import com.goorm.devlink.profileservice.service.ScheduleService;
import com.goorm.devlink.profileservice.util.ModelMapperUtil;
import com.goorm.devlink.profileservice.vo.ScheduleCreateRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class CalendarServiceImpl implements CalendarService {

    private final ProfileRepository profileRepository;
    private final CalendarRepository calendarRepository;
    private final ScheduleService scheduleService;
    private final ModelMapperUtil modelMapperUtil;

//    @Override
//    public CalendarDto getUserCalendar(String userUuid) {
//        CalendarEntity calendarEntity = calendarRepository.findByUserUuid(userUuid);
//        CalendarDto calendarDto = modelMapperUtil.convertToCalendarDto(calendarEntity);
//        return calendarDto;
//    }

    @Transactional
    @Override
    public void saveCalendarByScheduleCreateRequest(String userUuid, ScheduleCreateRequest scheduleCreateRequest) {

        ProfileEntity profileEntity = profileRepository.findByUserUuid(userUuid);
        CalendarEntity calendarEntity = calendarRepository.findByProfileEntity(profileEntity);
        if (calendarEntity != null) {
            scheduleService.saveScheduleByCalendarAndCreateRequest(calendarEntity, scheduleCreateRequest);
        } else {
            CalendarEntity newCalendarEntity = new CalendarEntity();
            newCalendarEntity.setProfileEntity(profileEntity);
            calendarRepository.save(newCalendarEntity);
            scheduleService.saveScheduleByCalendarAndCreateRequest(newCalendarEntity, scheduleCreateRequest);
        }
    }


}
