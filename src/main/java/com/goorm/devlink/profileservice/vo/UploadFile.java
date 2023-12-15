package com.goorm.devlink.profileservice.vo;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class UploadFile {
    private String uploadFilename;
    private String storeFilename;
}
