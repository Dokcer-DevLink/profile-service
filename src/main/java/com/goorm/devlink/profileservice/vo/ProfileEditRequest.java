package com.goorm.devlink.profileservice.vo;

import com.goorm.devlink.profileservice.entity.ProfileType;
import lombok.*;

import java.util.List;

@Getter @Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ProfileEditRequest {

    private String name;
    private String nickname;
    private ProfileType profileType;
    private String introduction;
    private int career; // is it years?
    private List<String> stacks;
    private String address;
}