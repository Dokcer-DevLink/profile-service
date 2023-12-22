package com.goorm.devlink.profileservice.controller;

import com.goorm.devlink.profileservice.service.CalendarService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class CalendarController {

    private final CalendarService calendarService;
}
