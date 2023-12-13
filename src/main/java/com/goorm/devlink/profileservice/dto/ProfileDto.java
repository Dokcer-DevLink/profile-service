package com.goorm.devlink.profileservice.dto;

import com.goorm.devlink.profileservice.entity.Profile;
import com.goorm.devlink.profileservice.entity.ProfileType;
import com.goorm.devlink.profileservice.vo.ProfileDetailRequest;
import lombok.Builder;
import lombok.Data;

import java.util.List;
import java.util.UUID;

@Data
@Builder
public class ProfileDto {

    private Long id;
    private String profileUuid;
    private String userUuid;
    private String profileImageUrl;
    private String name;
    private String nickname;
    private ProfileType profileType;
    private String introduction;
    private int career; // is it years?
    private List<String> stacks;
    private String address;

    public ProfileDto(Long id, String nickname) {
        this.id = id;
        this.nickname = nickname;
    }

    public ProfileDto(String profileImageUrl, String name, String nickname, String introduction, List<String> stacks, String address) {
        this.profileImageUrl = profileImageUrl;
        this.name = name;
        this.nickname = nickname;
        this.introduction = introduction;
        this.stacks = stacks;
        this.address = address;
    }

    public ProfileDto(Long id, String profileUuid, String profileImageUrl, String nickname, List<String> stacks, String address) {
        this.id = id;
        this.profileUuid = profileUuid;
        this.profileImageUrl = profileImageUrl;
        this.nickname = nickname;
        this.stacks = stacks;
        this.address = address;
    }

    public ProfileDto(Long id, String profileUuid, String userUuid, String profileImageUrl, String name, String nickname, String introduction, List<String> stacks, String address) {
        this.id = id;
        this.profileUuid = profileUuid;
        this.userUuid = userUuid;
        this.profileImageUrl = profileImageUrl;
        this.name = name;
        this.nickname = nickname;
        this.introduction = introduction;
        this.stacks = stacks;
        this.address = address;
    }

    public Profile convertToEntity() {
        Profile profileEntity = new Profile(profileImageUrl, name, nickname, introduction, stacks, address);
        return profileEntity;
    }

    public static ProfileDto getInstanceForCreate(ProfileDetailRequest profileDetailRequest, String userUuid) {
        return ProfileDto.builder()
                .profileUuid(UUID.randomUUID().toString())
                .userUuid(userUuid)
                .profileImageUrl(profileDetailRequest.getProfileImageUrl())
                .name(profileDetailRequest.getName())
                .nickname(profileDetailRequest.getNickname())
                .introduction(profileDetailRequest.getIntroduction())
                .stacks(profileDetailRequest.getStacks())
                .address(profileDetailRequest.getAddress())
                .build();
    }

    public static ProfileDto getInstanceForEdit(ProfileDetailRequest profileDetailRequest) {
        return ProfileDto.builder()
                .profileUuid(profileDetailRequest.getProfileUuid())
                .userUuid(profileDetailRequest.getUserUuid())
                .profileImageUrl(profileDetailRequest.getProfileImageUrl())
                .name(profileDetailRequest.getName())
                .nickname(profileDetailRequest.getNickname())
                .introduction(profileDetailRequest.getIntroduction())
                .stacks(profileDetailRequest.getStacks())
                .address(profileDetailRequest.getAddress())
                .build();
    }
}