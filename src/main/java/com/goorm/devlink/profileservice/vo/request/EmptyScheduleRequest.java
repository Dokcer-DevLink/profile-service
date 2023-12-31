package com.goorm.devlink.profileservice.vo.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;

@Getter
@Builder
@RequiredArgsConstructor
@AllArgsConstructor
public class EmptyScheduleRequest {

    List<String> userUuidList;
    private LocalDateTime startTime;
    private int unitTimeCount;
}
