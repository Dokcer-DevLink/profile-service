package com.goorm.devlink.profileservice.service;

import com.goorm.devlink.profileservice.dto.CalendarDto;
import com.goorm.devlink.profileservice.vo.ScheduleCreateRequest;

public interface CalendarService {

//    CalendarDto getUserCalendar(String userUuid);

    void saveCalendarByScheduleCreateRequest(String userUuid, ScheduleCreateRequest scheduleCreateRequest);
}
