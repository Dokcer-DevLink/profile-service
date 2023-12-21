package com.goorm.devlink.profileservice.entity;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;

@Entity
@Getter @Setter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Table(name = "Calendar")
public class CalendarEntity {

    @Id @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "calendar_id")
    private Long id;

    @OneToOne
    @JoinColumn(name = "profile_id")
    private ProfileEntity profileEntity;

    @OneToMany(mappedBy = "calendarEntity")
    private List<ScheduleEntity> scheduleEntities;
}
