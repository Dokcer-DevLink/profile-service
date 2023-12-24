package com.goorm.devlink.profileservice.dto;

import com.goorm.devlink.profileservice.vo.ScheduleCreateRequest;
import lombok.*;

import java.time.LocalDateTime;

@Data
@Getter
@Builder
@RequiredArgsConstructor
@AllArgsConstructor
public class ScheduleDto {

    private String mentoringUuid;
    private LocalDateTime startTime;
    private int unitTimeCount;

    public static ScheduleDto getInstanceForCreate(ScheduleCreateRequest scheduleCreateRequest) {
        return ScheduleDto.builder()
                .mentoringUuid(scheduleCreateRequest.getMentoringUuid())
                .startTime(scheduleCreateRequest.getStartTime())
                .unitTimeCount(scheduleCreateRequest.getUnitTimeCount())
                .build();
    }
}
