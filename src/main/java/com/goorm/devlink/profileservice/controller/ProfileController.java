package com.goorm.devlink.profileservice.controller;

import com.goorm.devlink.profileservice.dto.ProfileDto;
import com.goorm.devlink.profileservice.entity.ProfileType;
import com.goorm.devlink.profileservice.service.ProfileService;
import com.goorm.devlink.profileservice.service.S3UploadService;
import com.goorm.devlink.profileservice.vo.ProfileCommentResponse;
import com.goorm.devlink.profileservice.vo.ProfileCreateRequest;
import com.goorm.devlink.profileservice.vo.ProfileSimpleResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@RestController
@RequiredArgsConstructor
public class ProfileController {

    private ProfileService profileService;
    private S3UploadService s3UploadService;

    @Autowired
    public ProfileController(ProfileService profileService, S3UploadService s3UploadService) {
        this.profileService = profileService;
        this.s3UploadService = s3UploadService;
    }

    /** 마이프로필 조회 **/
    @GetMapping("/api/myprofile")
    public ProfileDto viewMyProfilePage(@RequestHeader("userUuid") String userUuid, @RequestParam("profileType") ProfileType profileType) {
        ProfileDto profileDto = profileService.getMyProfile(userUuid, profileType);
        return profileDto;
    }

    /** 마이프로필 생성 **/
    @PostMapping("/api/myprofile")
    public ResponseEntity<ProfileCommentResponse> createMyProfile(@RequestPart("data") ProfileCreateRequest profileCreateRequest,
                                                                  @RequestPart("file") MultipartFile file,
                                                                  @RequestHeader("userUuid") String userUuid) throws IOException {

        String profileImageUrl = s3UploadService.saveFile(file);

        ProfileDto profileDto = ProfileDto.getInstanceForCreate(profileCreateRequest, profileImageUrl, userUuid);
        String profileUuid = profileService.createProfile(profileDto);
        return new ResponseEntity<>(ProfileCommentResponse.getInstanceForCreate(profileUuid), HttpStatus.CREATED);
    }

//    /** 마이프로필 수정 **/
//    @PatchMapping("/api/myprofile")
//    public ResponseEntity<ProfileCommentResponse> editMyProfile(@RequestPart("data") ProfileCreateRequest profileCreateRequest,
//                                                                  @RequestPart("file") MultipartFile file,
//                                                                  @RequestHeader("userUuid") String userUuid) throws IOException {
//
//        String profileImageUrl = s3UploadService.saveFile(file);
//
//        ProfileDto profileDto = ProfileDto.getInstanceForCreate(profileCreateRequest, profileImageUrl, userUuid);
//        String profileUuid = profileService.createProfile(profileDto);
//        return new ResponseEntity<>(ProfileCommentResponse.getInstanceForCreate(profileUuid), HttpStatus.CREATED);
//    }

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

    /** 유저 스택 리스트 조회 **/
    @GetMapping("/api/profile/stacks")
    public List<String> viewUserStackList(@RequestHeader("userUuid") String userUuid, @RequestParam("profileUuid") String profileUuid) {
        ProfileDto profileDto = profileService.getProfileByUserUuidAndProfileUuid(userUuid, profileUuid);
        List<String> stacks = profileDto.getStacks();
        return stacks;
    }

    /** 간단한 유저 정보(프로필이미지 URL, 닉네임) 조회 **/
    @GetMapping("/api/profile/simpleInfo")
    public ProfileSimpleResponse viewUserSimpleInfo(@RequestHeader("userUuid") String userUuid, @RequestParam("profileUuid") String profileUuid) {
        ProfileDto profileDto = profileService.getProfileByUserUuidAndProfileUuid(userUuid, profileUuid);
        ProfileSimpleResponse profileSimpleResponse = new ProfileSimpleResponse(profileUuid, profileDto.getProfileImageUrl(), profileDto.getNickname());
        return profileSimpleResponse;
    }
}
