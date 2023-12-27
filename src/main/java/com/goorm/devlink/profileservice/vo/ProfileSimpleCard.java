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
public class ProfileSimpleCard {

    String userUuid;
    String profileImageUrl;
    String nickname;
    String address;
    List<String> stacks;

    public static ProfileSimpleCard getInstance(String userUuid, String profileImageUrl, String nickname, String address, List<String> stacks) {
        return ProfileSimpleCard.builder()
                .userUuid(userUuid)
                .profileImageUrl(profileImageUrl)
                .nickname(nickname)
                .address(address)
                .stacks(stacks)
                .build();
    }
}
