package com.goorm.devlink.profileservice.vo.request;

import lombok.*;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;

@Getter @Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ScheduleCreateRequest {

    @NotBlank(message = "{request.required}")
    private String mentoringUuid;
    @NotNull(message = "{request.required}")
    private LocalDateTime startTime;
    @NotNull(message = "{request.required}")
    private int unitTimeCount;
}
