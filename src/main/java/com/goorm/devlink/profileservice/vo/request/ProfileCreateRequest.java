package com.goorm.devlink.profileservice.vo.request;

import lombok.*;

@Getter @Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ProfileCreateRequest {

    private String nickname;
    private String githubAddress;
}
