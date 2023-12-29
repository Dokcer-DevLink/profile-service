package com.goorm.devlink.profileservice.vo.response;

import com.goorm.devlink.profileservice.vo.ProfileSimpleCard;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.data.domain.Slice;

import java.util.List;

@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ProfileSimpleCardListResponse {

    List<ProfileSimpleCard> profileSimpleCardList;
    private int pageNumber;
    private boolean last;

    public static ProfileSimpleCardListResponse getInstance(Slice<ProfileSimpleCard> profileSimpleCardSlice) {
        return ProfileSimpleCardListResponse.builder()
                .profileSimpleCardList(profileSimpleCardSlice.toList())
                .pageNumber(profileSimpleCardSlice.getPageable().getPageNumber())
                .last(profileSimpleCardSlice.isLast())
                .build();
    }
}
