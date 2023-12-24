package com.goorm.devlink.profileservice.service;

import com.goorm.devlink.profileservice.entity.CalendarEntity;
import com.goorm.devlink.profileservice.vo.ScheduleCreateRequest;

public interface ScheduleService {

    void saveScheduleByCalendar(CalendarEntity calendarEntity);

    void saveScheduleByCalendarAndCreateRequest(CalendarEntity calendarEntity, ScheduleCreateRequest scheduleCreateRequest);
}
