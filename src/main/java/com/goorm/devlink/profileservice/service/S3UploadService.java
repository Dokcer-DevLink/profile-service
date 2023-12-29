package com.goorm.devlink.profileservice.service;

import com.amazonaws.services.s3.AmazonS3Client;
import com.amazonaws.services.s3.model.ObjectMetadata;
import lombok.RequiredArgsConstructor;
import org.apache.commons.codec.binary.Base64;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;

@Service
@RequiredArgsConstructor
public class S3UploadService {

    private final AmazonS3Client amazonS3Client;

    @Value("${cloud.aws.s3.bucket}")
    private String bucketName;

    public String saveFile(String encodingImage, String userUuid) {

        byte[] decodedImage = Base64.decodeBase64(encodingImage);
        InputStream bis = new ByteArrayInputStream(decodedImage);

        ObjectMetadata metadata = new ObjectMetadata();
        metadata.setContentLength(decodedImage.length);
        metadata.setContentType("image/png"); // png, jpg

        String fileName = "profile/"+ userUuid;

        if (amazonS3Client.doesObjectExist(bucketName, fileName))
            amazonS3Client.deleteObject(bucketName, fileName);

        amazonS3Client.putObject(bucketName, fileName, bis, metadata);
        return amazonS3Client.getUrl(bucketName, fileName).toString();
    }
}
