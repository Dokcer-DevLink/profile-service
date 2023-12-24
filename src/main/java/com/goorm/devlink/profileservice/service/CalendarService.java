package com.goorm.devlink.profileservice.service;

import com.goorm.devlink.profileservice.dto.CalendarDto;
import com.goorm.devlink.profileservice.dto.ScheduleDto;
import com.goorm.devlink.profileservice.vo.ScheduleCreateRequest;

import java.util.List;

public interface CalendarService {

//    CalendarDto getUserCalendar(String userUuid);

    List<ScheduleDto> getCalendarScheduleDtos(String userUuid);

    void saveCalendarByScheduleCreateRequest(String userUuid, ScheduleCreateRequest scheduleCreateRequest);
}
