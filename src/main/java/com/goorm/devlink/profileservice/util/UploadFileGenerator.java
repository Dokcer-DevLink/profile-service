package com.goorm.devlink.profileservice.util;

import com.goorm.devlink.profileservice.vo.UploadFile;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.UUID;

@Component
public class UploadFileGenerator {

    public UploadFile generate(MultipartFile multipartFile) throws IOException {

        if (multipartFile.isEmpty()) {
            return null;
        }

        String originalFilename = multipartFile.getOriginalFilename();
        String storeFilename = UUID.randomUUID() + "." + extractExt(originalFilename);
        return new UploadFile(originalFilename, storeFilename);
    }

    private String extractExt(String originalFilename) {
        int pos = originalFilename.lastIndexOf(".");
        return originalFilename.substring(pos + 1);
    }
}
