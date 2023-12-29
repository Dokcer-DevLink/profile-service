package com.goorm.devlink.profileservice.service;

import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.model.ObjectMetadata;
import com.goorm.devlink.profileservice.util.UploadFileGenerator;
import com.goorm.devlink.profileservice.vo.UploadFile;
import lombok.RequiredArgsConstructor;
import org.apache.commons.codec.binary.Base64;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;

@Service
@RequiredArgsConstructor
public class S3UploadService {

    private final AmazonS3 amazonS3;
    private final UploadFileGenerator uploadFileGenerator;

    @Value("${cloud.aws.s3.bucket}")
    private String bucketName;

    public String saveFile(String encodingImage, String userUuid) throws IOException {

        byte[] decodedImage = Base64.decodeBase64(encodingImage);
        InputStream bis = new ByteArrayInputStream(decodedImage);

        ObjectMetadata metadata = new ObjectMetadata();
        metadata.setContentLength(decodedImage.length);
        metadata.setContentType("image/png"); // png, jpg

        String fileName = "profile/"+ userUuid;

        if (amazonS3.doesObjectExist(bucketName, fileName))
            amazonS3.deleteObject(bucketName, fileName);

        amazonS3.putObject(bucketName, fileName, bis, metadata);
        return amazonS3.getUrl(bucketName, fileName).toString();
        /////

//        ObjectMetadata metadata = new ObjectMetadata();
//        metadata.setContentLength(multipartFile.getSize());
//        metadata.setContentType(multipartFile.getContentType());
//
//        UploadFile uploadFile = uploadFileGenerator.generate(multipartFile);
//        String storeFilename = uploadFile.getStoreFilename();
//
//        amazonS3.putObject(bucketName, storeFilename, multipartFile.getInputStream(), metadata);
//        return amazonS3.getUrl(bucketName, storeFilename).toString();
    }
}
