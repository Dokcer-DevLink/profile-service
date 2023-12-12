package com.goorm.devlink.profileservice.controller;

import com.goorm.devlink.profileservice.dto.MyProfileDto;
import com.goorm.devlink.profileservice.dto.ProfileDto;
import com.goorm.devlink.profileservice.service.ProfileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

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
    public MyProfileDto viewMyProfilePage() {

        Long profileId = 1L; // 테스트용
        MyProfileDto myProfileDto = profileService.getMyPageProfile(profileId);
        return myProfileDto;
    }

    /** 마이프로필 편집 **/
    @PostMapping("/api/myprofile")
    public MyProfileDto editMyProfile(@RequestBody HashMap<String, Object> jsonObject, @RequestHeader("userUuid") String userUuid) {
        MyProfileDto myProfileDto = convertJsonToMyProfileDto(jsonObject);
        profileService.saveMyProfile(myProfileDto);
        return myProfileDto;
    }

    /** 마이프로필 삭제 **/
    @DeleteMapping("/api/myprofile")
    public String deleteMyProfile(@RequestParam("profileUuid") String profileUuid, @RequestHeader("userUuid") String userUuid) {
//        Long profileId = 1L;
        profileService.deleteProfileByProfileAndUserUuid(profileUuid, userUuid);
        return "Delete success.";
    }

    /** 프로필(타유저) 조회 **/
    @GetMapping("/api/profile")
    public ProfileDto viewProfilePage(@RequestParam("profileUuid") String profileUuid, @RequestHeader("userUuid") String userUuid) {
        ProfileDto profileDto = profileService.getProfileByProfileAndUserUuid(profileUuid, userUuid);
        return profileDto;
    }

    /** 프로필 리스트(검색) 조회 **/
    @GetMapping("/api/profile/list")
    public List<ProfileDto> viewProfilePage(@RequestParam("keyword") String listKeyword) {
        List<ProfileDto> profileListByKeyword = profileService.getProfileListByKeyword(listKeyword);
        return profileListByKeyword;
    }

    private static MyProfileDto convertJsonToMyProfileDto(HashMap<String, Object> jsonObject) {
        String imageUrl = jsonObject.get("imageUrl").toString();
        String name = jsonObject.get("name").toString();
        String nickname = jsonObject.get("nickname").toString();
        String introduction = jsonObject.get("introduction").toString();
        String stacks = jsonObject.get("stacks").toString();
        String address = jsonObject.get("address").toString();

        return new MyProfileDto(10L, imageUrl, name, nickname, introduction, stacks, address);
    }
}
