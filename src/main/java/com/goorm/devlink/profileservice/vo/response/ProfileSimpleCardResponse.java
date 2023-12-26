package com.goorm.devlink.profileservice.vo.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ProfileSimpleCardResponse {

    String userUuid;
    String profileImageUrl;
    String nickname;
    String address;
    List<String> stacks;

    public static ProfileSimpleCardResponse getInstance(String userUuid, String profileImageUrl, String nickname, String address, List<String> stacks) {
        return ProfileSimpleCardResponse.builder()
                .userUuid(userUuid)
                .profileImageUrl(profileImageUrl)
                .nickname(nickname)
                .address(address)
                .stacks(stacks)
                .build();
    }
}
