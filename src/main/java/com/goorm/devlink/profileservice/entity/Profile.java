package com.goorm.devlink.profileservice.entity;

import lombok.*;

import javax.persistence.*;
import java.util.List;
//import java.time.LocalDateTime;

@Entity
@Getter @Setter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@ToString(of = {})
public class Profile {

    @Id @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "profile_id")
    private Long id;
    @Column(name = "profile_uuid")
    private String profileUuid;
    @Column(name = "user_uuid")
    private String userUuid;
    @Column(name = "profile_image_url", unique = true)
    private String profileImageUrl;
    private String name;
    private String nickname;
    private String introduction;
    private int career; // is it years?
    @ElementCollection(fetch = FetchType.LAZY)
    private List<String> stacks;
    private String address;
    @Column(name = "profile_type")
    @Enumerated(EnumType.STRING)
    private ProfileType profileType;
//    @Column(name = "created_at")
//    private LocalDateTime createdAt;
//    @Column(name = "modified_at")
//    private LocalDateTime modifiedAt;
//    @Column(name = "is_deleted")
//    private boolean isDeleted;

    public Profile(String profileImageUrl, String name, String nickname, String introduction, List<String> stacks, String address) {
        this.profileImageUrl = profileImageUrl;
        this.name = name;
        this.nickname = nickname;
        this.introduction = introduction;
        this.stacks = stacks;
        this.address = address;
    }

    public Profile(String profileUuid, String userUuid, String profileImageUrl, String name, String nickname, String introduction, List<String> stacks, String address) {
        this.profileUuid = profileUuid;
        this.userUuid = userUuid;
        this.profileImageUrl = profileImageUrl;
        this.name = name;
        this.nickname = nickname;
        this.introduction = introduction;
        this.stacks = stacks;
        this.address = address;
    }
}
