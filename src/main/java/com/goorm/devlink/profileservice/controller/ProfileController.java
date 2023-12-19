package com.goorm.devlink.profileservice.controller;

import com.goorm.devlink.profileservice.dto.ProfileDto;
import com.goorm.devlink.profileservice.entity.ProfileType;
import com.goorm.devlink.profileservice.service.ProfileService;
import com.goorm.devlink.profileservice.service.S3UploadService;
import com.goorm.devlink.profileservice.vo.*;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Slice;
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
        profileService.testMethod();
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

//        String profileImageUrl = s3UploadService.saveFile(file);

        ProfileDto profileDto = ProfileDto.getInstanceForCreate(profileCreateRequest, "default-profileImageUrl", userUuid);
        profileService.createProfile(profileDto);
        return new ResponseEntity<>(ProfileCommentResponse.getInstanceForCreate(userUuid), HttpStatus.CREATED);
    }

    /** 마이프로필 수정 **/
    @PutMapping("/api/myprofile")
    public ResponseEntity<ProfileCommentResponse> editMyProfile(@RequestPart("data") ProfileEditRequest profileEditRequest,
                                                                  @RequestPart("file") MultipartFile file,
                                                                  @RequestHeader("userUuid") String userUuid) throws IOException {

        if (!file.isEmpty()) {
            String profileImageUrl = s3UploadService.saveFile(file);
            profileService.updateProfile(profileEditRequest, userUuid, profileImageUrl);
            return new ResponseEntity<>(ProfileCommentResponse.getInstanceForEdit(userUuid), HttpStatus.CREATED);
        } else {
            profileService.updateProfileWithoutImageUrl(profileEditRequest, userUuid);
            return new ResponseEntity<>(ProfileCommentResponse.getInstanceForEdit(userUuid), HttpStatus.CREATED);
        }
    }

    /** 마이프로필 삭제 **/
    @DeleteMapping("/api/myprofile")
    public String deleteMyProfile(@RequestHeader("userUuid") String userUuid) {
        profileService.deleteProfileByUserUuid(userUuid);
        return "Delete success.";
    }

    /** 프로필(타유저) 조회 **/
    @GetMapping("/api/profile")
    public ProfileDto viewProfilePage(@RequestHeader("userUuid") String userUuid) {
        ProfileDto profileDto = profileService.getProfileByUserUuid(userUuid);
        return profileDto;
    }

    /** 프로필 리스트(검색) 조회 **/
    @GetMapping("/api/profile/list")
    public ResponseEntity<Slice<ProfileSimpleCardResponse>> viewProfileList(@RequestParam("profileType") ProfileType profileType,
                                                                            @RequestParam("keyword") String keyword,
                                                                            @RequestParam("pageNumber") int pageNumber) {

        Slice<ProfileSimpleCardResponse> profileSimpleCardResponseSlice = profileService.getSimpleCardSliceByTypeAndKeyword(profileType, keyword, pageNumber);
        return new ResponseEntity<>(profileSimpleCardResponseSlice, HttpStatus.OK);
    }

    /** 추천 멘토/멘티 프로필 슬라이더 **/
    @GetMapping("/api/profile/slider")
    public ResponseEntity<Slice<ProfileSimpleCardResponse>> viewProfileSlider(@RequestParam("profileType") ProfileType profileType, @RequestParam("keyword") String keyword) {

        Slice<ProfileSimpleCardResponse> profileSimpleCardResponseSlice = profileService.getSimpleCardSliceByTypeAndKeyword(profileType, keyword, 0);
        return new ResponseEntity<>(profileSimpleCardResponseSlice, HttpStatus.OK);
    }

    /** 유저 스택 리스트 조회 **/
    @GetMapping("/api/profile/stacks")
    public List<String> viewUserStackList(@RequestHeader("userUuid") String userUuid) {
        ProfileDto profileDto = profileService.getProfileByUserUuid(userUuid);
        List<String> stacks = profileDto.getStacks();
        return stacks;
    }

    /** 간단한 유저 정보(프로필이미지 URL, 닉네임) 조회 **/
    @GetMapping("/api/profile/simpleInfo")
    public ProfileSimpleResponse viewUserSimpleInfo(@RequestHeader("userUuid") String userUuid) {
        ProfileDto profileDto = profileService.getProfileByUserUuid(userUuid);
        ProfileSimpleResponse profileSimpleResponse = new ProfileSimpleResponse(userUuid, profileDto.getProfileImageUrl(), profileDto.getNickname());
        return profileSimpleResponse;
    }
}
