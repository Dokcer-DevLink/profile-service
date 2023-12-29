package com.goorm.devlink.profileservice.controller;

import com.goorm.devlink.profileservice.dto.ScheduleDto;
import com.goorm.devlink.profileservice.service.CalendarService;
import com.goorm.devlink.profileservice.util.MessageUtil;
import com.goorm.devlink.profileservice.vo.response.CalendarViewResponse;
import com.goorm.devlink.profileservice.vo.request.ScheduleCreateRequest;
import com.goorm.devlink.profileservice.vo.response.ScheduleMessageResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.NoSuchElementException;

@RestController
@RequiredArgsConstructor
public class CalendarController {

    private final CalendarService calendarService;
    private final MessageUtil messageUtil;

    /** 유저 캘린더 조회 **/
    @GetMapping("/api/profile/schedule")
    public ResponseEntity<CalendarViewResponse> viewUserCalendar(@RequestParam("userUuid") String userUuid) {
        if (userUuid.isEmpty()) {
            throw new NoSuchElementException(messageUtil.getUserUuidEmptyMessage());
        }
        List<ScheduleDto> scheduleDtos = calendarService.getCalendarScheduleDtos(userUuid);
        CalendarViewResponse calendarViewResponse = CalendarViewResponse.getInstanceForResponse(scheduleDtos);
        return new ResponseEntity<>(calendarViewResponse, HttpStatus.ACCEPTED);
    }

    /** 스케줄 생성 **/
    @PostMapping("/api/myprofile/schedule")
    public ResponseEntity<ScheduleMessageResponse> createCalendarSchedule(@RequestHeader("userUuid") String userUuid, @Valid @RequestBody ScheduleCreateRequest scheduleCreateRequest) {
        if (userUuid.isEmpty()) {
            throw new NoSuchElementException(messageUtil.getUserUuidEmptyMessage());
        }
        if (scheduleCreateRequest.getMentoringUuid().isEmpty()) {
            throw new NoSuchElementException(messageUtil.getMentoringUuidEmptyMessage());
        }
        calendarService.saveCalendarByScheduleCreateRequest(userUuid, scheduleCreateRequest);
        return ResponseEntity.ok(ScheduleMessageResponse.getInstance(userUuid, scheduleCreateRequest.getMentoringUuid(), messageUtil.getScheduleCreateMessage()));
    }

    /** 스케줄 취소 **/
    @DeleteMapping("/api/myprofile/schedule")
    public ResponseEntity<ScheduleMessageResponse> cancelCalendarSchedule(@RequestHeader("userUuid") String userUuid, @RequestParam("mentoringUuid") String mentoringUuid) {
        if (userUuid.isEmpty()) {
            throw new NoSuchElementException(messageUtil.getUserUuidEmptyMessage());
        }
        if (mentoringUuid.isEmpty()) {
            throw new NoSuchElementException(messageUtil.getMentoringUuidEmptyMessage());
        }
        calendarService.deleteScheduleByUserUuidAndMentoringUuid(userUuid, mentoringUuid);
        return ResponseEntity.ok(ScheduleMessageResponse.getInstance(userUuid, mentoringUuid, messageUtil.getScheduleCancelMessage()));
    }
}