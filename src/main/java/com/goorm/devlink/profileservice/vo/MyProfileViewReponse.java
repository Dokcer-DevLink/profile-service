package com.goorm.devlink.profileservice.vo;

import com.goorm.devlink.profileservice.dto.ProfileDto;
import com.goorm.devlink.profileservice.dto.ScheduleDto;
import lombok.*;

import java.util.List;

@Getter @Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class MyProfileViewReponse {

    ProfileDto profile;
    List<ScheduleDto> schedules;

    public static MyProfileViewReponse getInstanceForResponse(ProfileDto profileDto, List<ScheduleDto> scheduleDtos) {
        return MyProfileViewReponse.builder()
                .profile(profileDto)
                .schedules(scheduleDtos)
                .build();
    }
}
