package com.goorm.devlink.profileservice.repository;

import com.goorm.devlink.profileservice.entity.CalendarEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CalendarRepository extends JpaRepository<CalendarEntity, Long> {
}
