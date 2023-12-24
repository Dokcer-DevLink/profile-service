package com.goorm.devlink.profileservice.service;

import com.goorm.devlink.profileservice.entity.CalendarEntity;
import com.goorm.devlink.profileservice.entity.ScheduleEntity;
import com.goorm.devlink.profileservice.vo.ScheduleCreateRequest;

import java.util.List;

public interface ScheduleService {

    void saveScheduleByCalendar(CalendarEntity calendarEntity);

    List<ScheduleEntity> getScheduleEntitiesByCalenderEntity(CalendarEntity calendarEntity);

    void saveScheduleByCalendarAndCreateRequest(CalendarEntity calendarEntity, ScheduleCreateRequest scheduleCreateRequest);
}
