package com.goorm.devlink.profileservice.vo.response;

import com.goorm.devlink.profileservice.dto.ScheduleDto;
import lombok.*;

import java.util.List;

@Getter @Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CalendarViewResponse {

    List<ScheduleDto> schedules;

    public static CalendarViewResponse getInstanceForResponse(List<ScheduleDto> scheduleDtos) {
        return CalendarViewResponse.builder()
                .schedules(scheduleDtos)
                .build();
    }
}
