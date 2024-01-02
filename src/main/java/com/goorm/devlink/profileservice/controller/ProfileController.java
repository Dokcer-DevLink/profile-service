package com.goorm.devlink.profileservice.controller;

import com.goorm.devlink.profileservice.dto.ProfileDto;
import com.goorm.devlink.profileservice.dto.ScheduleDto;
import com.goorm.devlink.profileservice.entity.constant.ProfileType;
import com.goorm.devlink.profileservice.service.CalendarService;
import com.goorm.devlink.profileservice.service.ProfileService;
import com.goorm.devlink.profileservice.util.MessageUtil;
import com.goorm.devlink.profileservice.vo.request.EmptyScheduleRequest;
import com.goorm.devlink.profileservice.vo.request.ProfileCreateRequest;
import com.goorm.devlink.profileservice.vo.request.ProfileEditRequest;
import com.goorm.devlink.profileservice.vo.response.*;
import com.goorm.devlink.profileservice.vo.ProfileSimpleCard;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.NoSuchElementException;

@RestController
@RequiredArgsConstructor
public class ProfileController {

    private final ProfileService profileService;
    private final CalendarService calendarService;
//    private final S3UploadService s3UploadService;
    private final MessageUtil messageUtil;

    /** 마이프로필 조회 **/
    @GetMapping("/api/myprofile")
    public ResponseEntity<MyProfileViewReponse> viewMyProfilePage(@RequestHeader("userUuid") String userUuid) {
        if (userUuid.isEmpty()) {
            throw new NoSuchElementException(messageUtil.getUserUuidEmptyMessage());
        }
        ProfileDto profileDto = profileService.getMyProfile(userUuid);
        List<ScheduleDto> scheduleDtos = calendarService.getCalendarScheduleDtos(userUuid);
        MyProfileViewReponse myProfileViewResponse = MyProfileViewReponse.getInstanceForResponse(profileDto, scheduleDtos);
        return new ResponseEntity<>(myProfileViewResponse, HttpStatus.OK);
    }

    /** 마이프로필 생성 **/
    @PostMapping("/api/myprofile")
    public ResponseEntity createMyProfile(@RequestHeader("userUuid") String userUuid,
                                          @Valid @RequestBody ProfileCreateRequest profileCreateRequest) {
        if (userUuid.isEmpty()) {
            throw new NoSuchElementException(messageUtil.getUserUuidEmptyMessage());
        }
        ProfileDto profileDto = ProfileDto.getInstanceForCreate(userUuid, profileCreateRequest);
        profileService.createProfile(profileDto);
        return ResponseEntity.ok(ProfileMessageResponse.getInstance(userUuid, messageUtil.getProfileCreateMessage()));
    }

    /** 마이프로필 수정 **/
//    @PutMapping("/api/myprofile")
//    public ResponseEntity<MyProfileViewReponse> editMyProfile(@Valid @RequestBody ProfileEditRequest profileEditRequest,
//                                        @RequestHeader("userUuid") String userUuid) {
//
//        if (userUuid.isEmpty()) {
//            throw new NoSuchElementException(messageUtil.getUserUuidEmptyMessage());
//        }
//
//        ProfileDto profileDto;
//        if (profileEditRequest.getFileData() != null) {
//            String profileImageUrl = s3UploadService.saveFile(profileEditRequest.getFileData(), userUuid);
//            profileDto = profileService.updateProfile(profileEditRequest, userUuid, profileImageUrl);
//        } else {
//            profileDto = profileService.updateProfileWithoutImageUrl(profileEditRequest, userUuid);
//        }
//        List<ScheduleDto> scheduleDtos = calendarService.getCalendarScheduleDtos(userUuid);
//        MyProfileViewReponse myProfileViewResponse = MyProfileViewReponse.getInstanceForResponse(profileDto, scheduleDtos);
//
//        return new ResponseEntity<>(myProfileViewResponse, HttpStatus.OK);
////        return ResponseEntity.ok(ProfileMessageResponse.getInstance(userUuid, messageUtil.getProfileUpdateMessage()));
//    }

    @PutMapping("/api/myprofile")
    public ResponseEntity<MyProfileViewReponse> editMyProfile(@Valid @RequestBody ProfileEditRequest request,
                                                              @RequestHeader("userUuid") String userUuid) {
        if (userUuid.isEmpty()) {
            throw new NoSuchElementException(messageUtil.getUserUuidEmptyMessage());
        }

        ProfileDto profileDto = profileService.newUpdateProfile(request, userUuid);
        List<ScheduleDto> scheduleDtos = calendarService.getCalendarScheduleDtos(userUuid);
        MyProfileViewReponse myProfileViewResponse = MyProfileViewReponse.getInstanceForResponse(profileDto, scheduleDtos);
        return ResponseEntity.ok(myProfileViewResponse);
    }

    /** 마이프로필 삭제 **/
    @DeleteMapping("/api/myprofile")
    public ResponseEntity deleteMyProfile(@RequestHeader("userUuid") String userUuid) {
        if (userUuid.isEmpty()) {
            throw new NoSuchElementException(messageUtil.getUserUuidEmptyMessage());
        }
        profileService.deleteProfileByUserUuid(userUuid);
        return ResponseEntity.ok(ProfileMessageResponse.getInstance(userUuid, messageUtil.getProfileDeleteMessage()));
    }

    /** 프로필(타유저) 조회 **/
    @GetMapping("/api/profile")
    public ResponseEntity<ProfileDto> viewProfilePage(@RequestParam("userUuid") String userUuid) {
        if (userUuid.isEmpty()) {
            throw new NoSuchElementException(messageUtil.getUserUuidEmptyMessage());
        }
        ProfileDto profileDto = profileService.getProfileByUserUuid(userUuid);
        return new ResponseEntity<>(profileDto, HttpStatus.ACCEPTED);
    }

    /** 프로필 리스트(검색) 조회 **/
    @GetMapping("/api/profile/list")
    public ResponseEntity<Slice<ProfileSimpleCard>> viewProfileList(@RequestParam("profileType") ProfileType profileType,
                                                                    @RequestParam("keyword") String keyword,
                                                                    @PageableDefault(page = 0, size = 100) Pageable pageable) {

        Slice<ProfileSimpleCard> profileSimpleCardSlice = profileService.getSimpleCardSliceByTypeAndKeyword(profileType, keyword, pageable);
        return new ResponseEntity<>(profileSimpleCardSlice, HttpStatus.OK);
    }

    /** 추천 멘토/멘티 프로필 슬라이더 **/
    @GetMapping("/api/profile/recommend")
    public ResponseEntity<List<ProfileSimpleCard>> viewProfileSlider(@RequestParam("profileType") ProfileType profileType) {

        List<ProfileSimpleCard> profileSimpleCardList = profileService.getSimpleCardListForRecommend(profileType);
        return new ResponseEntity<>(profileSimpleCardList, HttpStatus.OK);
    }

    /** 유저 스택 리스트 조회 **/
    @GetMapping("/api/profile/stacks")
    public ResponseEntity<List<String>> viewUserStackList(@RequestParam("userUuid") String userUuid) {
        if (userUuid.isEmpty()) {
            throw new NoSuchElementException(messageUtil.getUserUuidEmptyMessage());
        }
        ProfileDto profileDto = profileService.getProfileByUserUuid(userUuid);
        List<String> stacks = profileDto.getStacks();
        return new ResponseEntity<>(stacks, HttpStatus.OK);
    }

    /** 간단한 유저 정보(프로필이미지 URL, 닉네임) 조회 **/
    @GetMapping("/api/profile/chat")
    public ResponseEntity<List<ProfileSimpleResponse>> getProfileSimpleInfo(@RequestParam("userUuidList") List<String> userUuidList) {
        if (userUuidList == null) {
            throw new NoSuchElementException(messageUtil.getUserUuidEmptyMessage());
        }
        List<ProfileSimpleResponse> profileSimpleResponseList = profileService.getProfileByUserUuidList(userUuidList);
        return new ResponseEntity<>(profileSimpleResponseList, HttpStatus.OK);
    }

    /** 지정 시간 이외 가용 유저 리스트 조회 **/
    @PostMapping("/api/profile/enableUsers")
    public ResponseEntity<EmptyScheduleResponse> findEnableUser(@RequestBody EmptyScheduleRequest emptyScheduleRequest) {
        List<String> receivedUserUuidList = emptyScheduleRequest.getUserUuidList();
        List<String> enableUserUuidList = calendarService.findEnableUserUuidListByValidCalendar(
                receivedUserUuidList,
                emptyScheduleRequest.getStartTime(),
                emptyScheduleRequest.getUnitTimeCount());
        EmptyScheduleResponse emptyScheduleResponse = EmptyScheduleResponse.builder().userUuidList(enableUserUuidList).build();
        return new ResponseEntity<>(emptyScheduleResponse, HttpStatus.OK);
    }

    /** 받은 멘토링 신청 프로필 카드 리스트 조회 **/
    @GetMapping("/api/profile/apply")
    public ResponseEntity<List<ProfileSimpleCard>> getMentoringAppliedProfiles(@RequestParam("userUuidList") List<String> userUuidList) {
        List<ProfileSimpleCard> simpleCardList = profileService.getSimpleCardListByUserUuidList(userUuidList);
        return new ResponseEntity<>(simpleCardList, HttpStatus.OK);
    }
}
