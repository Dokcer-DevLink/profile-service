package com.goorm.devlink.profileservice.controller;

import com.amazonaws.services.s3.AmazonS3ClientBuilder;
import com.goorm.devlink.profileservice.dto.ProfileDto;
import com.goorm.devlink.profileservice.entity.ProfileType;
import com.goorm.devlink.profileservice.service.ProfileService;
import com.goorm.devlink.profileservice.util.AwsS3Uploader;
import com.goorm.devlink.profileservice.vo.ProfileCommentResponse;
import com.goorm.devlink.profileservice.vo.ProfileCreateRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Base64;
import java.util.List;

import com.amazonaws.AmazonServiceException;
import com.amazonaws.regions.Regions;
import com.amazonaws.services.s3.AmazonS3;

@RestController
public class ProfileController {

    private ProfileService profileService;


    @Autowired
    public ProfileController(ProfileService profileService) {
        this.profileService = profileService;
        profileService.testMethod();
    }

    /** 마이프로필 조회 **/
    @GetMapping("/api/myprofile")
    public ProfileDto viewMyProfilePage(@RequestHeader("userUuid") String userUuid, @RequestParam("profileType") ProfileType profileType) {
        ProfileDto profileDto = profileService.getMyProfile(userUuid, profileType);
        return profileDto;
    }

    /** 마이프로필 편집 **/
    @PostMapping("/api/myprofile")
    public ResponseEntity<ProfileCommentResponse> createMyProfile(@RequestBody ProfileCreateRequest profileCreateRequest, @RequestHeader("userUuid") String userUuid) throws IOException {

        //===== 파일 업로드 테스트 =====//
//        System.out.format("Uploading %s to S3 bucket %s...\n", file_path, bucket_name);
//        final AmazonS3 s3 = AmazonS3ClientBuilder.standard().withRegion(Regions.DEFAULT_REGION).build();
//        try {
//            s3.putObject(bucket_name, key_name, new File(file_path));
//        } catch (AmazonServiceException e) {
//            System.err.println(e.getErrorMessage());
//            System.exit(1);
//        }


        ProfileDto profileDto = ProfileDto.getInstanceForCreate(profileCreateRequest, userUuid);
        String profileUuid = profileService.createProfile(profileDto);
        return new ResponseEntity<>(ProfileCommentResponse.getInstanceForCreate(profileUuid), HttpStatus.CREATED);
    }

    /** 마이프로필 삭제 **/
    @DeleteMapping("/api/myprofile")
    public String deleteMyProfile(@RequestHeader("userUuid") String userUuid, @RequestParam("profileUuid") String profileUuid) {
        profileService.deleteProfileByUserAndProfileUuid(userUuid, profileUuid);
        return "Delete success.";
    }

    /** 프로필(타유저) 조회 **/
    @GetMapping("/api/profile")
    public ProfileDto viewProfilePage(@RequestHeader("userUuid") String userUuid, @RequestParam("profileUuid") String profileUuid) {
        ProfileDto profileDto = profileService.getProfileByUserUuidAndProfileUuid(userUuid, profileUuid);
        return profileDto;
    }

    /** 프로필 리스트(검색) 조회 **/
    @GetMapping("/api/profile/list")
    public List<ProfileDto> viewProfilePage(@RequestParam("profileType") ProfileType profileType, @RequestParam("keyword") String keyword) {
        List<ProfileDto> profileListByKeyword = profileService.getProfileListByTypeAndKeyword(profileType, keyword);
        return profileListByKeyword;
    }
}
