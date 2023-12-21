package com.goorm.devlink.profileservice.entity;

import lombok.*;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Getter @Setter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Table(name = "Schedule")
public class ScheduleEntity {

    @Id @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "schedule_id")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_uuid")
//    @OneToMany(mappedBy = "team")
//    @Column(name = "user_uuid")
//    private String userUuid;
    private ProfileEntity profileEntity;

//    @OneToOne
//    @JoinColumn(name = "mentoring_id")
//    private MentoringEntity mentoringEntity;
    @Column(name = "mentoring_uuid")
    private String mentoringUuid;
    @Column(name = "start_time")
    private LocalDateTime startTime;
    @Column(name = "end_time")
    private LocalDateTime endTime;

//    @OneToOne
//    @JoinColumn(name = "post_id")
//    private PostEntity postEntity;
    @Column(name = "post_uuid")
    private String postUuid;
    @Column(name = "running_time")
    private int runningTime;
}
