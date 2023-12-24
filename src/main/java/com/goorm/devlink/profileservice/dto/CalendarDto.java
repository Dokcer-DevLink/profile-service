package com.goorm.devlink.profileservice.dto;

import com.goorm.devlink.profileservice.vo.ScheduleCreateRequest;
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
