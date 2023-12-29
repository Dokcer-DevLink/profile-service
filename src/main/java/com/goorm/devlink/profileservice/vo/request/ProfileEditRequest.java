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
    private String nickname;
    @NotNull(message = "{request.required}")
    private String githubAddress;
    @NotNull(message = "{request.required}")
    private ProfileType profileType;
    @NotNull(message = "{request.required}")
    private String introduction;
    @NotNull(message = "{request.required}")
    private List<String> stacks;
    @NotNull(message = "{request.required}")
    private String address;
}
