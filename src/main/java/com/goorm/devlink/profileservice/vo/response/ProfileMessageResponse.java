package com.goorm.devlink.profileservice.vo.response;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Builder
@Getter @Setter
public class ProfileMessageResponse {

    private String userUuid;
    private String message;

    public static ProfileMessageResponse getInstance(String userUuid, String message) {
        return ProfileMessageResponse.builder()
                .userUuid(userUuid)
                .message(message)
                .build();
    }
}
