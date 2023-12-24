package com.goorm.devlink.profileservice.entity;

import lombok.*;

import javax.persistence.*;
import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor
//@RequiredArgsConstructor
@Table(name = "Calendar")
public class CalendarEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "calendar_id")
    private Long id;

    @OneToOne
    @JoinColumn(name = "profile_id")
    private ProfileEntity profileEntity;

    @OneToMany(mappedBy = "calendarEntity", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<ScheduleEntity> scheduleEntities;
}
