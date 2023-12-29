package com.goorm.devlink.profileservice.service;

import com.goorm.devlink.profileservice.dto.ScheduleDto;
import com.goorm.devlink.profileservice.vo.request.ScheduleCreateRequest;

import java.time.LocalDateTime;
import java.util.List;

public interface CalendarService {

    List<ScheduleDto> getCalendarScheduleDtos(String userUuid);

    void saveCalendarByScheduleCreateRequest(String userUuid, ScheduleCreateRequest scheduleCreateRequest);

    void deleteScheduleByUserUuidAndMentoringUuid(String userUuid, String mentoringUuid);

    List<String> findEnableUserUuidListByValidCalendar(List<String> userUuidList, LocalDateTime startTime, int unitTimeCount);
}
