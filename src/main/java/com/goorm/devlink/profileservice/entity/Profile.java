package com.goorm.devlink.profileservice.entity;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
//import java.time.LocalDateTime;

@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@ToString(of = {})
public class Profile {

    @Id @GeneratedValue
    @Column(name = "profile_id")
    private Long id;
    @Column(name = "profile_uuid")
    private String profileUuid;
    @Column(name = "user_uuid")
    private String userUuid;
    @Column(name = "image_url")
    private String imageUrl;
    private String name;
    private String nickname;
    private String introduction;
    //    private int career; // int?
    private String stacks;
    private String address;
//    private Enum<ProfileType> type;
//    @Column(name = "created_at")
//    private LocalDateTime createdAt;
//    @Column(name = "modified_at")
//    private LocalDateTime modifiedAt;
//    @Column(name = "is_deleted")
//    private boolean isDeleted;


    public Profile(String nickname) {
        this.nickname = nickname;
    }

    public Profile(String imageUrl, String name, String nickname, String introduction, String stacks, String address) {
        this.imageUrl = imageUrl;
        this.name = name;
        this.nickname = nickname;
        this.introduction = introduction;
        this.stacks = stacks;
        this.address = address;
    }

    public Profile(String profileUuid, String userUuid, String imageUrl, String name, String nickname, String introduction, String stacks, String address) {
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
