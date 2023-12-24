package com.goorm.devlink.profileservice.entity;

import lombok.*;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Getter @Setter
@NoArgsConstructor
@Table(name = "Schedule")
public class ScheduleEntity {

    @Id @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "schedule_id")
    private Long id;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "calendar_id")
    private CalendarEntity calendarEntity;

    @Column(name = "mentoring_uuid")
    private String mentoringUuid;
    @Column(name = "start_time")
    private LocalDateTime startTime;
    private int unitTimeCount;
}
