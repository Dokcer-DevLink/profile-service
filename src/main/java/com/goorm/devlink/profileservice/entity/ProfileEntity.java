package com.goorm.devlink.profileservice.entity;

import lombok.*;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

//import java.time.LocalDateTime;

@Entity
@Getter @Setter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@ToString(of = {})
@Table(name = "Profile")
public class ProfileEntity {

    @Id @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "profile_id")
    private Long id;
    @Column(name = "user_uuid")
    private String userUuid;
    @Column(name = "profile_image_url", unique = true)
    private String profileImageUrl;
    private String name;
    private String nickname;
    private String introduction;
    private int career; // is it years?
    private String address;
    @Column(name = "profile_type")
    @Enumerated(EnumType.STRING)
    private ProfileType profileType;

    @ElementCollection(fetch = FetchType.LAZY)
    @CollectionTable(name = "stack", joinColumns = @JoinColumn(name = "profile_id"))
    private List<String> stacks;

    @OneToMany(mappedBy = "profileEntity")
    private List<ScheduleEntity> scheduleEntities = new ArrayList<>();
//    @Column(name = "created_at")
//    private LocalDateTime createdAt;
//    @Column(name = "modified_at")
//    private LocalDateTime modifiedAt;
//    @Column(name = "is_deleted")
//    private boolean isDeleted;

    public ProfileEntity(String name, String nickname, String introduction, String address, List<String> stacks) {
//        this.profileImageUrl = profileImageUrl;
        this.name = name;
        this.nickname = nickname;
        this.introduction = introduction;
        this.address = address;
        this.stacks = stacks;
    }

    public ProfileEntity(String userUuid, String name, String nickname, String introduction, String address, List<String> stacks) {
        this.userUuid = userUuid;
//        this.profileImageUrl = profileImageUrl;
        this.name = name;
        this.nickname = nickname;
        this.introduction = introduction;
        this.address = address;
        this.stacks = stacks;
    }

    public static ProfileEntity getInstanceTest(int i, ProfileType profileType, String userUuid, List<String> stacks){
        ProfileEntity profileEntity = new ProfileEntity();
        profileEntity.setUserUuid(userUuid);
        profileEntity.setProfileImageUrl("profile_image_url" + i);
        profileEntity.setName("name" + i);
        profileEntity.setNickname("nickname" + i);
        profileEntity.setIntroduction("introduction" + i);
        profileEntity.setCareer(i%5);
        profileEntity.setProfileType(i%3==0?ProfileType.MENTOR:(i%3==1?ProfileType.MENTEE:ProfileType.BOTH));
        profileEntity.setAddress("address"+i);
        profileEntity.stacks = stacks;

        return profileEntity;
    }
}
