package com.goorm.devlink.profileservice.repository;

import com.goorm.devlink.profileservice.entity.CalendarEntity;
import com.goorm.devlink.profileservice.entity.ScheduleEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ScheduleRepository extends JpaRepository<ScheduleEntity, Long> {

    List<ScheduleEntity> findByCalendarEntity(CalendarEntity calendarEntity);

    @Modifying
    @Query("DELETE FROM ScheduleEntity s where s.mentoringUuid = :mentoringUuid")
    void deleteByMentoringUuid(@Param("mentoringUuid") String mentoringUuid);
}
