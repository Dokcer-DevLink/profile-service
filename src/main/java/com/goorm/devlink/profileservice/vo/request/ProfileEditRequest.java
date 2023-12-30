package com.goorm.devlink.profileservice.vo.request;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.goorm.devlink.profileservice.entity.constant.ProfileType;
import lombok.*;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.List;

@Getter @Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ProfileEditRequest {

    private String fileData;
    @NotBlank(message = "{request.required}")
    @NotNull(message = "{request.required}")
    private String nickname;
    private String githubAddress;
    private ProfileType profileType;
    private String introduction;
    private List<String> stacks;
    private String address;
}
