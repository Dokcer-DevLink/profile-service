package com.goorm.devlink.profileservice.service;

import com.goorm.devlink.profileservice.repository.CalendarRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CalendarService {

    private final CalendarRepository calendarRepository;
}
