package com.goorm.devlink.profileservice.dto;

import com.goorm.devlink.profileservice.entity.Profile;
//import com.goorm.devlink.profileservice.entity.ProfileType;
import lombok.Data;

@Data
public class MyProfileDto {
    private Long id;
//    private String userUuid;
    private String imageUrl;
    private String name;
    private String nickname;
//    private Enum<ProfileType> profileType;
    private String introduction;
//    private int career; // int?
    private String stacks;
    private String address;

    public MyProfileDto(Long id, String imageUrl, String name, String nickname, String introduction, String stacks, String address) {
        this.id = id;
        this.imageUrl = imageUrl;
        this.name = name;
        this.nickname = nickname;
        this.introduction = introduction;
        this.stacks = stacks;
        this.address = address;
    }

    public Profile convertToEntity() {
        Profile profileEntity = new Profile(imageUrl, name, nickname, introduction, stacks, address);
        return profileEntity;
    }
}