package com.goorm.devlink.profileservice.vo;

import lombok.*;

import java.time.LocalDateTime;

@Getter @Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ScheduleCreateRequest {

    private String mentoringUuid;
    private LocalDateTime startTime;
    private int unitTimeCount;
}
