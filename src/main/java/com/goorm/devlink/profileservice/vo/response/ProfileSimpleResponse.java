package com.goorm.devlink.profileservice.vo.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
@AllArgsConstructor
public class ProfileSimpleResponse {

    String userUuid;
    String profileImageUrl;
    String nickname;

    public static ProfileSimpleResponse getInstance(String userUuid, String profileImageUrl, String nickname) {
        return ProfileSimpleResponse.builder()
                .userUuid(userUuid)
                .profileImageUrl(profileImageUrl)
                .nickname(nickname)
                .build();
    }
}
