package com.goorm.devlink.profileservice.controller;

import com.goorm.devlink.profileservice.dto.ProfileDto;
import com.goorm.devlink.profileservice.dto.ScheduleDto;
import com.goorm.devlink.profileservice.entity.constant.ProfileType;
import com.goorm.devlink.profileservice.service.CalendarService;
import com.goorm.devlink.profileservice.service.ProfileService;
import com.goorm.devlink.profileservice.service.S3UploadService;
import com.goorm.devlink.profileservice.vo.request.ProfileCreateRequest;
import com.goorm.devlink.profileservice.vo.request.ProfileEditRequest;
import com.goorm.devlink.profileservice.vo.response.MyProfileViewReponse;
import com.goorm.devlink.profileservice.vo.ProfileSimpleCard;
import com.goorm.devlink.profileservice.vo.response.ProfileSimpleCardListResponse;
import com.goorm.devlink.profileservice.vo.response.ProfileSimpleResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Slice;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.Valid;
import java.io.IOException;
import java.util.List;

@RestController
@RequiredArgsConstructor
public class ProfileController {

    private final ProfileService profileService;
    private final S3UploadService s3UploadService;
    private final CalendarService calendarService;

    /** 마이프로필 조회 **/
    @GetMapping("/api/myprofile")
    public ResponseEntity<MyProfileViewReponse> viewMyProfilePage(@RequestHeader("userUuid") String userUuid) {
        ProfileDto profileDto = profileService.getMyProfile(userUuid);
        List<ScheduleDto> scheduleDtos = calendarService.getCalendarScheduleDtos(userUuid);
        MyProfileViewReponse myProfileViewResponse = MyProfileViewReponse.getInstanceForResponse(profileDto, scheduleDtos);
        return new ResponseEntity<>(myProfileViewResponse, HttpStatus.OK);
    }

    /** 마이프로필 생성 **/
    @PostMapping("/api/myprofile")
    public ResponseEntity createMyProfile(@RequestHeader("userUuid") String userUuid,
                                          @Valid @RequestBody ProfileCreateRequest profileCreateRequest) {

        ProfileDto profileDto = ProfileDto.getInstanceForCreate(userUuid, profileCreateRequest);
        profileService.createProfile(profileDto);
        return ResponseEntity.ok().build();
    }

    /** 마이프로필 수정 **/
    @PutMapping("/api/myprofile")
    public ResponseEntity editMyProfile(@Valid @RequestPart("data") ProfileEditRequest profileEditRequest,
                                        @Valid @RequestPart("file") MultipartFile file,
                                        @RequestHeader("userUuid") String userUuid) throws IOException {

        if (!file.isEmpty()) {
            String profileImageUrl = s3UploadService.saveFile(file);
            profileService.updateProfile(profileEditRequest, userUuid, profileImageUrl);
            return ResponseEntity.accepted().build();
        } else {
            profileService.updateProfileWithoutImageUrl(profileEditRequest, userUuid);
            return ResponseEntity.accepted().build();
        }
    }

    /** 마이프로필 삭제 **/
    @DeleteMapping("/api/myprofile")
    public ResponseEntity deleteMyProfile(@RequestHeader("userUuid") String userUuid) {
        profileService.deleteProfileByUserUuid(userUuid);
        return ResponseEntity.ok().build();
    }

    /** 프로필(타유저) 조회 **/
    @GetMapping("/api/profile")
    public ResponseEntity<ProfileDto> viewProfilePage(@RequestParam("userUuid") String userUuid) {
        ProfileDto profileDto = profileService.getProfileByUserUuid(userUuid);
        return new ResponseEntity<>(profileDto, HttpStatus.ACCEPTED);
    }

    /** 프로필 리스트(검색) 조회 **/
    @GetMapping("/api/profile/list")
    public ResponseEntity<ProfileSimpleCardListResponse> viewProfileList(@RequestParam("profileType") ProfileType profileType,
                                                                    @RequestParam("keyword") String keyword,
                                                                    @RequestParam("pageNumber") int pageNumber) {

        Slice<ProfileSimpleCard> profileSimpleCardSlice = profileService.getSimpleCardSliceByTypeAndKeyword(profileType, keyword, pageNumber);
        ProfileSimpleCardListResponse profileSimpleCardListResponse = ProfileSimpleCardListResponse.getInstance(profileSimpleCardSlice);
        return new ResponseEntity<>(profileSimpleCardListResponse, HttpStatus.OK);
    }

    /** 추천 멘토/멘티 프로필 슬라이더 **/
    @GetMapping("/api/profile/slider")
    public ResponseEntity<List<ProfileSimpleCard>> viewProfileSlider(@RequestParam("profileType") ProfileType profileType, @RequestParam("keyword") String keyword) {

        Slice<ProfileSimpleCard> profileSimpleCardSlice = profileService.getSimpleCardSliceByTypeAndKeyword(profileType, keyword, 0);
        return new ResponseEntity<>(profileSimpleCardSlice.toList(), HttpStatus.OK);
    }

    /** 유저 스택 리스트 조회 **/
    @GetMapping("/api/profile/stacks")
    public ResponseEntity<List<String>> viewUserStackList(@RequestParam("userUuid") String userUuid) {
        ProfileDto profileDto = profileService.getProfileByUserUuid(userUuid);
        List<String> stacks = profileDto.getStacks();
        return new ResponseEntity<>(stacks, HttpStatus.OK);
    }

    /** 간단한 유저 정보(프로필이미지 URL, 닉네임) 조회 **/
    @GetMapping("/api/profile/simpleInfo")
    public ResponseEntity<ProfileSimpleResponse> viewUserSimpleInfo(@RequestParam("userUuid") String userUuid) {
        ProfileDto profileDto = profileService.getProfileByUserUuid(userUuid);
        ProfileSimpleResponse profileSimpleResponse = new ProfileSimpleResponse(userUuid, profileDto.getProfileImageUrl(), profileDto.getNickname());
        return new ResponseEntity<>(profileSimpleResponse, HttpStatus.OK);
    }
}
