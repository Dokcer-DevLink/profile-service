package com.goorm.devlink.profileservice.entity;

import lombok.*;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Getter @Setter
@NoArgsConstructor
@Table(name = "Schedule")
public class ScheduleEntity {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "schedule_id")
    private Long id;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "calendar_id")
    private CalendarEntity calendarEntity;

    @Column(name = "mentoring_uuid")
    private String mentoringUuid;
    @Column(name = "start_time")
    private LocalDateTime startTime;
    @Column(name = "unit_time_count")
    private int unitTimeCount;
}
