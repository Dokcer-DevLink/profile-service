package com.goorm.devlink.profileservice.dto;

import com.goorm.devlink.profileservice.entity.ProfileType;
import com.goorm.devlink.profileservice.vo.ProfileCreateRequest;
import com.goorm.devlink.profileservice.vo.ProfileEditRequest;
import lombok.*;

import java.util.Arrays;
import java.util.List;
import java.util.UUID;

@Data
@Getter
@Builder
@RequiredArgsConstructor
@AllArgsConstructor
public class ProfileDto {

    private String userUuid;
    private String profileImageUrl;
    private String name;
    private String nickname;
    private ProfileType profileType;
    private String introduction;
    private int career; // is it years?
    private String address;
    private List<String> stacks;

    public ProfileDto(String profileImageUrl, String name, String nickname, ProfileType profileType, String introduction, String address, List<String> stacks) {
        this.profileImageUrl = profileImageUrl;
        this.name = name;
        this.nickname = nickname;
        this.profileType = profileType;
        this.introduction = introduction;
        this.address = address;
        this.stacks = stacks;
    }

    public static ProfileDto getInstanceForCreate(ProfileCreateRequest profileCreateRequest, String profileImageUrl, String userUuid) {
        return ProfileDto.builder()
                .userUuid(userUuid)
                .profileImageUrl(profileImageUrl)
                .name(UUID.randomUUID().toString())
                .nickname(UUID.randomUUID().toString())
                .profileType(ProfileType.NULL)
                .introduction("자기소개를 입력해주세요.")
                .career(0)
                .stacks(null)
                .address("지역을 입력해주세요.")
                .build();
    }
}