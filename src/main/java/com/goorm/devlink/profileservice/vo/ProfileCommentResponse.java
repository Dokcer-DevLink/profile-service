package com.goorm.devlink.profileservice.vo;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class ProfileCommentResponse {

    private String userUuid;
    private String message;

    public static ProfileCommentResponse getInstanceForCreate(String userUuid) {
        return ProfileCommentResponse.builder()
                .userUuid(userUuid)
                .message("Profile is created.")
                .build();
    }

    public static ProfileCommentResponse getInstanceForEdit(String userUuid) {
        return ProfileCommentResponse.builder()
                .userUuid(userUuid)
                .message("Profile is updated.")
                .build();
    }
}
