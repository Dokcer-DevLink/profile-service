package com.goorm.devlink.profileservice.vo;

import com.goorm.devlink.profileservice.entity.ProfileType;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter @Setter
@Builder
public class ProfileCreateRequest {

    private String profileImageUrl;
    private String name;
    private String nickname;
    private ProfileType profileType;
    private String introduction;
    private int career;
    private List<String> stacks;
    private String address;
}
