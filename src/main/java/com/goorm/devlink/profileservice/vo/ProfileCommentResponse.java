package com.goorm.devlink.profileservice.vo;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class ProfileCommentResponse {

    private String profileUuid;
    private String message;

    public static ProfileCommentResponse getInstanceForCreate(String profileUuid) {
        return ProfileCommentResponse.builder()
                .profileUuid(profileUuid)
                .message("Profile is created.")
                .build();
    }
}
