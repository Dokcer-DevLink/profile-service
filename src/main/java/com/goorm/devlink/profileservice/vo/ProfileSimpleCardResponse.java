package com.goorm.devlink.profileservice.vo;

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

    String profileUuid;
    String profileImageUrl;
    String nickname;
    String address;
    List<String> stacks;

    public static ProfileSimpleCardResponse getInstance(String profileUuid, String profileImageUrl, String nickname, String address, List<String> stacks) {
        return ProfileSimpleCardResponse.builder()
                .profileUuid(profileUuid)
                .profileImageUrl(profileImageUrl)
                .nickname(nickname)
                .address(address)
                .stacks(stacks)
                .build();
    }
}
