package com.goorm.devlink.profileservice.dto;

import lombok.*;

import java.util.List;

@Data
@Getter
@Builder
@RequiredArgsConstructor
@AllArgsConstructor
public class CalendarDto {

    private String userUuid;
    private List<ScheduleDto> scheduleDtos;
}
