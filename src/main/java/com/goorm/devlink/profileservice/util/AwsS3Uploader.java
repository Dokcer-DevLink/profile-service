package com.goorm.devlink.profileservice.util;

import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.model.ObjectMetadata;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@Component
@RequiredArgsConstructor
public class AwsS3Uploader {

    private final AmazonS3 amazonS3;

//    @Value("${cloud.aws.s3.bucket}")
//    private String bucket;
//
//    public String saveFile(MultipartFile multipartFile) throws IOException {
//        String originalFilename = multipartFile.getOriginalFilename();
//
//        ObjectMetadata metadata = new ObjectMetadata();
//        metadata.setContentLength(multipartFile.getSize());
//        metadata.setContentType(multipartFile.getContentType());
//
//        amazonS3.putObject(bucket, originalFilename, multipartFile.getInputStream(), metadata);
//        return amazonS3.getUrl(bucket, originalFilename).toString();
//    }
}