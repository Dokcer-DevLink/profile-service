package com.goorm.devlink.profileservice.vo.request;

import lombok.*;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Getter @Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ProfileCreateRequest {

    @NotBlank(message = "{request.required}")
    private String nickname;
    private String githubAddress;
}
