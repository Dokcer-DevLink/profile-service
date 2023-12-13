package com.goorm.devlink.profileservice.dto;

import com.goorm.devlink.profileservice.entity.ProfileEntity;
import com.goorm.devlink.profileservice.entity.ProfileType;
import com.goorm.devlink.profileservice.vo.ProfileCreateRequest;
import com.goorm.devlink.profileservice.vo.ProfileDetailRequest;
import lombok.*;

import java.util.List;
import java.util.UUID;

@Data
@Getter
@Builder
@RequiredArgsConstructor
@AllArgsConstructor
public class ProfileDto {

    private String profileUuid;
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

    public static ProfileDto getInstanceForCreate(ProfileCreateRequest profileCreateRequest, String userUuid) {
        return ProfileDto.builder()
                .profileUuid(UUID.randomUUID().toString())
                .userUuid(userUuid)
                .profileImageUrl(profileCreateRequest.getProfileImageUrl())
                .name(profileCreateRequest.getName())
                .nickname(profileCreateRequest.getNickname())
                .profileType(profileCreateRequest.getProfileType())
                .introduction(profileCreateRequest.getIntroduction())
                .career(profileCreateRequest.getCareer())
                .stacks(profileCreateRequest.getStacks())
                .address(profileCreateRequest.getAddress())
                .build();
    }

    public static ProfileDto getInstanceForEdit(ProfileDetailRequest profileDetailRequest) {
        return ProfileDto.builder()
                .profileUuid(profileDetailRequest.getProfileUuid())
                .userUuid(profileDetailRequest.getUserUuid())
                .profileImageUrl(profileDetailRequest.getProfileImageUrl())
                .name(profileDetailRequest.getName())
                .nickname(profileDetailRequest.getNickname())
                .profileType(profileDetailRequest.getProfileType())
                .introduction(profileDetailRequest.getIntroduction())
                .career(profileDetailRequest.getCareer())
                .stacks(profileDetailRequest.getStacks())
                .address(profileDetailRequest.getAddress())
                .build();
    }
}