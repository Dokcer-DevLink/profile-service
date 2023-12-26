package com.goorm.devlink.profileservice.service.impl;

import com.goorm.devlink.profileservice.dto.ScheduleDto;
import com.goorm.devlink.profileservice.entity.CalendarEntity;
import com.goorm.devlink.profileservice.entity.ScheduleEntity;
import com.goorm.devlink.profileservice.repository.ScheduleRepository;
import com.goorm.devlink.profileservice.service.ScheduleService;
import com.goorm.devlink.profileservice.util.ModelMapperUtil;
import com.goorm.devlink.profileservice.vo.request.ScheduleCreateRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ScheduleServiceImpl implements ScheduleService {

    private final ScheduleRepository scheduleRepository;
    private final ModelMapperUtil modelMapperUtil;

    @Transactional
    @Override
    public void saveScheduleByCalendar(CalendarEntity calendarEntity) {
        ScheduleEntity scheduleEntity = new ScheduleEntity();
        scheduleEntity.setCalendarEntity(calendarEntity);
        scheduleRepository.save(scheduleEntity);
    }

    @Override
    public List<ScheduleEntity> getScheduleEntitiesByCalenderEntity(CalendarEntity calendarEntity) {
        return scheduleRepository.findByCalendarEntityAndStartTimeIsAfterThanCurrentTime(calendarEntity, LocalDateTime.now());
    }

    @Transactional
    @Override
    public void saveScheduleByCalendarAndCreateRequest(CalendarEntity calendarEntity, ScheduleCreateRequest scheduleCreateRequest) throws RuntimeException {

        ScheduleDto scheduleDto = ScheduleDto.getInstanceForCreate(scheduleCreateRequest);
        ScheduleEntity scheduleEntity = modelMapperUtil.convertToScheduleEntity(scheduleDto);
        scheduleEntity.setCalendarEntity(calendarEntity);


        if (scheduleValidationCheck(scheduleEntity)) {
            try {
                scheduleRepository.save(scheduleEntity);
            } catch (Exception e) {
                throw new RuntimeException("Schedule creation error.");
            }
        } else {
            throw new RuntimeException("There are schedules already exist in overlapping time zone.");
        }
    }

    @Transactional
    @Override
    public void deleteScheduleByCalendarEntityAndMentoringUuid(CalendarEntity calendarEntity, String mentoringUuid) {

        try {
            scheduleRepository.deleteByCalendarEntityAndMentoringUuid(calendarEntity, mentoringUuid);
        } catch (Exception e) {
            throw new RuntimeException("Schedule delete error.");
        }
    }

    private boolean scheduleValidationCheck(ScheduleEntity scheduleEntity) {

        LocalDateTime startTimeToValidCheck = scheduleEntity.getStartTime();
        LocalDateTime endTimeToValidCheck = startTimeToValidCheck.plusMinutes(scheduleEntity.getUnitTimeCount() * 30);

        List<ScheduleEntity> allSchedules = scheduleRepository.findByCalendarEntity(scheduleEntity.getCalendarEntity());
        for (ScheduleEntity existSchedule : allSchedules) {
            LocalDateTime existStartTime = existSchedule.getStartTime();
            LocalDateTime existEndTime = existStartTime.plusMinutes(existSchedule.getUnitTimeCount() * 30);
            if ((startTimeToValidCheck.isAfter(existStartTime) && startTimeToValidCheck.isBefore(existEndTime)) ||
                    (endTimeToValidCheck.isAfter(existStartTime) && endTimeToValidCheck.isBefore(existEndTime)))
                return false;
        }
        return true;
    }
}
