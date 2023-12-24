package com.goorm.devlink.profileservice.repository;

import com.goorm.devlink.profileservice.entity.CalendarEntity;
import com.goorm.devlink.profileservice.entity.ScheduleEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDateTime;
import java.util.List;

public interface ScheduleRepository extends JpaRepository<ScheduleEntity, Long> {

    @Query("SELECT s FROM ScheduleEntity s " +
            "WHERE s.calendarEntity = :calendarEntity AND s.startTime >= :currentTime " +
            "ORDER BY s.startTime ASC")
    List<ScheduleEntity> findByCalendarEntityAndStartTimeIsAfterThanCurrentTime(
            @Param("calendarEntity") CalendarEntity calendarEntity,
            @Param("currentTime") LocalDateTime currentTime);

    @Modifying
    @Query("DELETE FROM ScheduleEntity s where s.mentoringUuid = :mentoringUuid")
    void deleteByMentoringUuid(@Param("mentoringUuid") String mentoringUuid);
}
