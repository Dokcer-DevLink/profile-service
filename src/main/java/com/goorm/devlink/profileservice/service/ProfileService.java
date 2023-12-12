package com.goorm.devlink.profileservice.service;

import com.goorm.devlink.profileservice.dto.MyProfileDto;
import com.goorm.devlink.profileservice.dto.ProfileDto;
import com.goorm.devlink.profileservice.entity.Profile;
import com.goorm.devlink.profileservice.repository.ProfileRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProfileService {

    private final ProfileRepository profileRepository;

    @Autowired
    public ProfileService(ProfileRepository profileRepository) {
        this.profileRepository = profileRepository;

        // 테스트용
        String profileUuid = "123";
        String userUuid = "456";
        String imageUrl = "http://hello";
        String name = "kim";
        String nickname = "kkk";
        String introduction = "hello kim";
        String stacks = "#java #spring";
        String address = "seoul mapo";

        Profile profile = new Profile(profileUuid, userUuid, imageUrl, name, nickname, introduction, stacks, address);
        profileRepository.save(profile);
    }

    public ProfileDto getProfileByProfileUuid(String profileUuid) {
        ProfileDto byProfileUuid = profileRepository.findByProfileUuid(profileUuid);
        return byProfileUuid;
    }

    public ProfileDto getProfileByProfileAndUserUuid(String profileUuid, String userUuid) {
        ProfileDto byUuid = profileRepository.findByProfileAndUserUuid(profileUuid, userUuid);
        return byUuid;
    }

    public List<ProfileDto> getProfileListByKeyword(String keyword) {
        List<ProfileDto> profilesByStackKeyword = profileRepository.findProfilesByStackKeyword(keyword);
        return profilesByStackKeyword;
    }

    public MyProfileDto getMyPageProfile(Long id) {
        MyProfileDto myProfileDtoById = profileRepository.findMyProfileDtoById(id);
        return myProfileDtoById;
    }

    public void save(Profile profile) {
        profileRepository.save(profile);
    }

    public void saveMyProfile(MyProfileDto myProfileDto) {

        Profile profile = myProfileDto.convertToEntity();
        profileRepository.save(profile);
    }

    public void deleteProfileByProfileAndUserUuid(String profileUuid, String userUuid) {
        profileRepository.deleteByProfileAndUserUuid(profileUuid, userUuid);
    }
}
