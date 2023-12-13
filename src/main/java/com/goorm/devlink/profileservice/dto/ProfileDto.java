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
public class ProfileDto {

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

//    public ProfileDto(String nickname) {
//        this.nickname = nickname;
//    }
//
    public ProfileDto(String profileImageUrl, String name, String nickname, String introduction, List<String> stacks, String address) {
        this.profileImageUrl = profileImageUrl;
        this.name = name;
        this.nickname = nickname;
        this.introduction = introduction;
        this.stacks = stacks;
        this.address = address;
    }
//
//    public ProfileDto(String profileUuid, String profileImageUrl, String nickname, List<String> stacks, String address) {
//        this.profileUuid = profileUuid;
//        this.profileImageUrl = profileImageUrl;
//        this.nickname = nickname;
//        this.stacks = stacks;
//        this.address = address;
//    }
//
    public ProfileDto(String profileUuid, String userUuid, String profileImageUrl, String name, String nickname, String introduction, int career, List<String> stacks, String address) {
        this.profileUuid = profileUuid;
        this.userUuid = userUuid;
        this.profileImageUrl = profileImageUrl;
        this.name = name;
        this.nickname = nickname;
        this.introduction = introduction;
        this.career = career;
        this.stacks = stacks;
        this.address = address;
    }

//    public ProfileEntity convertToEntity() {
//        ProfileEntity profileEntity = new ProfileEntity(profileImageUrl, name, nickname, introduction, stacks, address);
//        return profileEntity;
//    }

//    public ProfileDto convertEntityToDto(ProfileEntity profileEntity) {
//        ProfileDto profileDto = new ProfileDto();
//        profileEntity.getProfileImageUrl()
//    }

    public static ProfileDto getInstanceForCreate(ProfileCreateRequest profileCreateRequest, String userUuid) {
        return ProfileDto.builder()
                .profileUuid(UUID.randomUUID().toString())
                .userUuid(userUuid)
                .profileImageUrl(profileCreateRequest.getProfileImageUrl())
                .name(profileCreateRequest.getName())
                .nickname(profileCreateRequest.getNickname())
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
                .introduction(profileDetailRequest.getIntroduction())
                .career(profileDetailRequest.getCareer())
                .stacks(profileDetailRequest.getStacks())
                .address(profileDetailRequest.getAddress())
                .build();
    }
}