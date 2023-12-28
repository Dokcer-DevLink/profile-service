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

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "profile_id")
    private Long id;
    @Column(name = "user_uuid")
    private String userUuid;
    @Column(name = "profile_image_url", unique = true)
    private String profileImageUrl;
    private String nickname;
    @Column(name = "github_address")
    private String githubAddress;
    private String introduction;
    private String address;
    @Column(name = "profile_type")
    @Enumerated(EnumType.STRING)
    private ProfileType profileType;

    @ElementCollection(fetch = FetchType.LAZY)
    @CollectionTable(name = "stack", joinColumns = @JoinColumn(name = "profile_id"))
    private List<String> stacks;

    @OneToOne(mappedBy = "profileEntity", cascade = CascadeType.ALL, orphanRemoval = true)
    private CalendarEntity calendarEntity;
}
