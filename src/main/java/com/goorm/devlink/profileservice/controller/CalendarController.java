package com.goorm.devlink.profileservice.controller;

import com.goorm.devlink.profileservice.dto.CalendarDto;
import com.goorm.devlink.profileservice.dto.ProfileDto;
import com.goorm.devlink.profileservice.service.CalendarService;
import com.goorm.devlink.profileservice.service.ProfileService;
import com.goorm.devlink.profileservice.vo.ScheduleCreateRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
public class CalendarController {

    private final CalendarService calendarService;

    @PostMapping("/api/profile/schedule")
    public ResponseEntity setUserCalendarSchedule(@RequestHeader("userUuid") String userUuid, @RequestBody ScheduleCreateRequest scheduleCreateRequest) {

//        CalendarDto calendarDto = calendarService.getUserCalendar(userUuid);
        calendarService.saveCalendarByScheduleCreateRequest(userUuid, scheduleCreateRequest);
        return ResponseEntity.ok().build();
    }
}