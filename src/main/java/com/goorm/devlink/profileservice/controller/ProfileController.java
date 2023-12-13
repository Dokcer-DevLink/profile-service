package com.goorm.devlink.profileservice.controller;

import com.goorm.devlink.profileservice.dto.MyProfileDto;
import com.goorm.devlink.profileservice.dto.ProfileDto;
import com.goorm.devlink.profileservice.entity.ProfileEntity;
import com.goorm.devlink.profileservice.entity.ProfileType;
import com.goorm.devlink.profileservice.service.ProfileService;
import com.goorm.devlink.profileservice.vo.ProfileCommentResponse;
import com.goorm.devlink.profileservice.vo.ProfileCreateRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;

@RestController
public class ProfileController {

    private ProfileService profileService;


    @Autowired
    public ProfileController(ProfileService profileService) {
        this.profileService = profileService;
    }

    /** 마이프로필 조회 **/
    @GetMapping("/api/myprofile")
    public void viewMyProfilePage(@RequestHeader("userUuid") String userUuid, @RequestParam("profileType") ProfileType profileType) {

        Long profileId = 1L; // 테스트용
        ProfileEntity profileEntity = profileService.getMyProfile(userUuid, profileType);
//        return profileDto;
    }

    /** 마이프로필 편집 **/
    @PostMapping("/api/myprofile")
    public ResponseEntity<ProfileCommentResponse> editMyProfile(@RequestBody ProfileCreateRequest profileCreateRequest, @RequestHeader("userUuid") String userUuid) {
//        ProfileDto profileDto = convertJsonToMyProfileDto(jsonObject);
        ProfileDto profileDto = ProfileDto.getInstanceForCreate(profileCreateRequest, userUuid);
        String profileUuid = profileService.createProfile(profileDto);
        return new ResponseEntity<>(ProfileCommentResponse.getInstanceForCreate(profileUuid), HttpStatus.CREATED);
    }

    /** 마이프로필 삭제 **/
//    @DeleteMapping("/api/myprofile")
//    public String deleteMyProfile(@RequestParam("profileUuid") String profileUuid, @RequestHeader("userUuid") String userUuid) {
////        Long profileId = 1L;
//        profileService.deleteProfileByProfileAndUserUuid(profileUuid, userUuid);
//        return "Delete success.";
//    }

    /** 프로필(타유저) 조회 **/
//    @GetMapping("/api/profile")
//    public ProfileDto viewProfilePage(@RequestParam("profileUuid") String profileUuid, @RequestHeader("userUuid") String userUuid) {
//        ProfileDto profileDto = profileService.getProfileByProfileAndUserUuid(profileUuid, userUuid);
//        return profileDto;
//    }

    /** 프로필 리스트(검색) 조회 **/
//    @GetMapping("/api/profile/list")
//    public List<ProfileDto> viewProfilePage(@RequestParam("keyword") String listKeyword) {
//        List<ProfileDto> profileListByKeyword = profileService.getProfileListByKeyword(listKeyword);
//        return profileListByKeyword;
//    }

//    private static ProfileDto convertJsonToMyProfileDto(HashMap<String, Object> jsonObject) {
//        String profileImageUrl = jsonObject.get("profileImageUrl").toString();
//        String name = jsonObject.get("name").toString();
//        String nickname = jsonObject.get("nickname").toString();
//        String introduction = jsonObject.get("introduction").toString();
////        List<String> stacks = jsonObject.get("stacks").
//        List<String> stacks = jsonObject.get("stacks");
//        String address = jsonObject.get("address").toString();
//
//        return new ProfileDto(profileImageUrl, name, nickname, introduction, stacks, address);
//    }

//    private static HashMap<String, Object> convert(Object obj) {
//        List<String> a = new ArrayList<>();
//
//    }
}
