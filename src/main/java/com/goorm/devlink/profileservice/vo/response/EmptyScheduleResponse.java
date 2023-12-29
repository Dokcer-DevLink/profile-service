package com.goorm.devlink.profileservice.vo.response;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter @Setter
@Builder
public class EmptyScheduleResponse {

    List<String> userUuidList;

    public static EmptyScheduleResponse getInstance(List<String> userUuidList) {
        return EmptyScheduleResponse.builder()
                .userUuidList(userUuidList)
                .build();
    }
}
