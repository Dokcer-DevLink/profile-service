package com.goorm.devlink.profileservice.dto;

//import com.goorm.devlink.profileservice.entity.ProfileType;
import lombok.Data;

@Data
public class ProfileDto {

    private Long id;
    private String profileUuid;
    private String userUuid;
    private String imageUrl;
    private String name;
    private String nickname;
//    private Enum<ProfileType> profileType;
    private String introduction;
//    private int career; // int?
    private String stacks;
    private String address;

    public ProfileDto(Long id, String nickname) {
        this.id = id;
        this.nickname = nickname;
    }

    public ProfileDto(Long id, String profileUuid, String imageUrl, String nickname, String stacks, String address) {
        this.id = id;
        this.profileUuid = profileUuid;
        this.imageUrl = imageUrl;
        this.nickname = nickname;
        this.stacks = stacks;
        this.address = address;
    }

    public ProfileDto(Long id, String profileUuid, String userUuid, String imageUrl, String name, String nickname, String introduction, String stacks, String address) {
        this.id = id;
        this.profileUuid = profileUuid;
        this.userUuid = userUuid;
        this.imageUrl = imageUrl;
        this.name = name;
        this.nickname = nickname;
        this.introduction = introduction;
        this.stacks = stacks;
        this.address = address;
    }
}