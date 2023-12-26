package com.goorm.devlink.profileservice.vo.request;

import lombok.Data;

@Data
public class ProfileImageRequest {

    private String base64;
    private String fileName;
    private int fileSize;
    private String type;
}
