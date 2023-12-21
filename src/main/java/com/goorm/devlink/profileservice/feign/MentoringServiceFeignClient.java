package com.goorm.devlink.profileservice.feign;

import com.goorm.devlink.profileservice.dto.ScheduleDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;

@FeignClient(name = "mentoring-service")
public interface MentoringServiceFeignClient {

    @GetMapping("/api/mentoring/schedule")
    public ScheduleDto viewUserSchedulesTmp(@RequestHeader("userUuid") String userUuid);
}
