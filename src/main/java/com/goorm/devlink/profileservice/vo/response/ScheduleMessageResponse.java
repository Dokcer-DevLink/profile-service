package com.goorm.devlink.profileservice.vo.response;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Builder
@Getter @Setter
public class ScheduleMessageResponse {

    private String userUuid;
    private String mentoringUuid;
    private String message;

    public static ScheduleMessageResponse getInstance(String userUuid, String mentoringUuid, String message) {
        return ScheduleMessageResponse.builder()
                .userUuid(userUuid)
                .mentoringUuid(mentoringUuid)
                .message(message)
                .build();
    }
}
