package com.goorm.devlink.profileservice.entity;

import com.goorm.devlink.profileservice.entity.constant.ProfileType;
import lombok.*;

import javax.persistence.*;
import java.util.List;

@Entity
@Getter @Setter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
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
    private int career;
    private String address;
    @Column(name = "profile_type")
    @Enumerated(EnumType.STRING)
    private ProfileType profileType;

    @ElementCollection(fetch = FetchType.LAZY)
    @CollectionTable(name = "stack", joinColumns = @JoinColumn(name = "profile_id"))
    private List<String> stacks;

    @OneToOne(mappedBy = "profileEntity")
    private CalendarEntity calendarEntity;

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
