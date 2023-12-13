package com.goorm.devlink.profileservice.entity;

import com.goorm.devlink.profileservice.dto.ProfileDto;
import lombok.*;
import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.CascadeType;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

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
    private String address;
    @Column(name = "profile_type")
    @Enumerated(EnumType.STRING)
    private ProfileType profileType;

    @ElementCollection(fetch = FetchType.LAZY)
    @CollectionTable(name = "stack", joinColumns = @JoinColumn(name = "profile_id"))
//    @Cascade({CascadeType.REMOVE})
    private List<String> stacks;
//    @Column(name = "created_at")
//    private LocalDateTime createdAt;
//    @Column(name = "modified_at")
//    private LocalDateTime modifiedAt;
//    @Column(name = "is_deleted")
//    private boolean isDeleted;

    public ProfileEntity(String profileImageUrl, String name, String nickname, String introduction, String address, List<String> stacks) {
        this.profileImageUrl = profileImageUrl;
        this.name = name;
        this.nickname = nickname;
        this.introduction = introduction;
        this.address = address;
        this.stacks = stacks;
    }

    public ProfileEntity(String profileUuid, String userUuid, String profileImageUrl, String name, String nickname, String introduction, String address, List<String> stacks) {
        this.profileUuid = profileUuid;
        this.userUuid = userUuid;
        this.profileImageUrl = profileImageUrl;
        this.name = name;
        this.nickname = nickname;
        this.introduction = introduction;
        this.address = address;
        this.stacks = stacks;
    }

//    public ProfileDto convertToDto() {
//        new ProfileDto(this.profileImageUrl, this.name, this.nickname, this.introduction, this.career, this.stacks, this.address);
//    }

    public static ProfileEntity getInstanceTest(int i, ProfileType profileType, String userUuid, List<String> stacks){
        ProfileEntity profileEntity = new ProfileEntity();
        profileEntity.setUserUuid(userUuid);
        profileEntity.setProfileUuid(UUID.randomUUID().toString());
        profileEntity.setProfileImageUrl("profile_image_url");
        profileEntity.setName("name");
        profileEntity.setNickname("nickname");
        profileEntity.setIntroduction("introduction");
        profileEntity.setCareer(2);
        profileEntity.setProfileType(ProfileType.MENTOR);
        profileEntity.setAddress("address"+i);
        profileEntity.stacks = stacks;

        return profileEntity;
    }
}
