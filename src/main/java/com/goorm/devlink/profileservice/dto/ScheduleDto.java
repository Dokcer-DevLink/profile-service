package com.goorm.devlink.profileservice.dto;

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
}
