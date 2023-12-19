package com.goorm.devlink.profileservice.vo;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
@AllArgsConstructor
public class ProfileSimpleResponse {

    String profileUuid;
    String profileImageUrl;
    String nickname;

    public static ProfileSimpleResponse getInstance(String profileUuid, String profileImageUrl, String nickname) {
        return ProfileSimpleResponse.builder()
                .profileUuid(profileUuid)
                .profileImageUrl(profileImageUrl)
                .nickname(nickname)
                .build();
    }
}
