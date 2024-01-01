package com.goorm.devlink.profileservice.repository;

import com.goorm.devlink.profileservice.entity.CalendarEntity;
import com.goorm.devlink.profileservice.entity.ProfileEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
public interface CalendarRepository extends JpaRepository<CalendarEntity, Long> {

    @Transactional(readOnly = true)
    CalendarEntity findByProfileEntity(ProfileEntity profileEntity);

    @Transactional(readOnly = true)
    List<CalendarEntity> findByProfileEntityIn(List<ProfileEntity> profileEntityList);
}
