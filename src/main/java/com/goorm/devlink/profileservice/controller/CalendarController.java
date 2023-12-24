package com.goorm.devlink.profileservice.controller;

import com.goorm.devlink.profileservice.dto.CalendarDto;
import com.goorm.devlink.profileservice.dto.ProfileDto;
import com.goorm.devlink.profileservice.dto.ScheduleDto;
import com.goorm.devlink.profileservice.service.CalendarService;
import com.goorm.devlink.profileservice.service.ProfileService;
import com.goorm.devlink.profileservice.vo.CalendarViewResponse;
import com.goorm.devlink.profileservice.vo.ScheduleCreateRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class CalendarController {

    private final CalendarService calendarService;

    @GetMapping("/api/profile/schedule")
    public ResponseEntity<CalendarViewResponse> viewUserCalendar(@RequestParam("userUuid") String userUuid) {
        List<ScheduleDto> scheduleDtos = calendarService.getCalendarScheduleDtos(userUuid);
        CalendarViewResponse calendarViewResponse = CalendarViewResponse.getInstanceForResponse(scheduleDtos);
        return new ResponseEntity<>(calendarViewResponse, HttpStatus.ACCEPTED);
    }

    @PostMapping("/api/profile/schedule")
    public ResponseEntity setUserCalendarSchedule(@RequestHeader("userUuid") String userUuid, @RequestBody ScheduleCreateRequest scheduleCreateRequest) {

//        CalendarDto calendarDto = calendarService.getUserCalendar(userUuid);
        calendarService.saveCalendarByScheduleCreateRequest(userUuid, scheduleCreateRequest);
        return ResponseEntity.ok().build();
    }
}