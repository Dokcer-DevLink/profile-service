package com.goorm.devlink.profileservice.controller;

import com.goorm.devlink.profileservice.dto.ScheduleDto;
import com.goorm.devlink.profileservice.service.CalendarService;
import com.goorm.devlink.profileservice.vo.response.CalendarViewResponse;
import com.goorm.devlink.profileservice.vo.request.ScheduleCreateRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequiredArgsConstructor
public class CalendarController {

    private final CalendarService calendarService;

    /** 유저 캘린더 조회 **/
    @GetMapping("/api/profile/schedule")
    public ResponseEntity<CalendarViewResponse> viewUserCalendar(@RequestParam("userUuid") String userUuid) {
        List<ScheduleDto> scheduleDtos = calendarService.getCalendarScheduleDtos(userUuid);
        CalendarViewResponse calendarViewResponse = CalendarViewResponse.getInstanceForResponse(scheduleDtos);
        return new ResponseEntity<>(calendarViewResponse, HttpStatus.ACCEPTED);
    }

    /** 스케줄 생성 **/
    @PostMapping("/api/myprofile/schedule")
    public ResponseEntity createCalendarSchedule(@RequestHeader("userUuid") String userUuid, @Valid @RequestBody ScheduleCreateRequest scheduleCreateRequest) {
        calendarService.saveCalendarByScheduleCreateRequest(userUuid, scheduleCreateRequest);
        return ResponseEntity.ok().build();
    }

    /** 스케줄 취소 **/
    @DeleteMapping("/api/myprofile/schedule")
    public ResponseEntity cancelCalendarSchedule(@RequestHeader("userUuid") String userUuid, @RequestParam("mentoringUuid") String mentoringUuid) {
        calendarService.deleteScheduleByUserUuidAndMentoringUuid(userUuid, mentoringUuid);
        return ResponseEntity.accepted().build();
    }
}