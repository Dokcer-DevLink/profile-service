package com.goorm.devlink.profileservice.service;

import com.goorm.devlink.profileservice.dto.ScheduleDto;
import com.goorm.devlink.profileservice.vo.ScheduleCreateRequest;

import java.util.List;

public interface CalendarService {

    List<ScheduleDto> getCalendarScheduleDtos(String userUuid);

    void saveCalendarByScheduleCreateRequest(String userUuid, ScheduleCreateRequest scheduleCreateRequest);

    void deleteScheduleByUserUuidAndMentoringUuid(String userUuid, String mentoringUuid);
}
