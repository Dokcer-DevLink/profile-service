package com.goorm.devlink.profileservice.dto;

import com.goorm.devlink.profileservice.entity.constant.ProfileType;
import com.goorm.devlink.profileservice.vo.request.ProfileCreateRequest;
import lombok.*;

import java.util.List;

@Data
@Getter
@Builder
@RequiredArgsConstructor
@AllArgsConstructor
public class ProfileDto {

    private String userUuid;
    private String profileImageUrl;
    private String nickname;
    private String githubAddress;
    private ProfileType profileType;
    private String introduction;
    private String address;
    private List<String> stacks;

    public ProfileDto(String profileImageUrl, String nickname, String githubAddress, ProfileType profileType, String introduction, String address, List<String> stacks) {
        this.profileImageUrl = profileImageUrl;
        this.nickname = nickname;
        this.githubAddress = githubAddress;
        this.profileType = profileType;
        this.introduction = introduction;
        this.address = address;
        this.stacks = stacks;
    }

    public static ProfileDto getInstanceForCreate(String userUuid, ProfileCreateRequest profileCreateRequest) {
        return ProfileDto.builder()
                .userUuid(userUuid)
                .nickname(profileCreateRequest.getNickname())
                .githubAddress(profileCreateRequest.getGithubAddress())
                .build();
    }
}