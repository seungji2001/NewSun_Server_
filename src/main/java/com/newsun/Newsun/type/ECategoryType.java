package com.newsun.Newsun.type;

import com.newsun.Newsun.exception.CustomException;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.util.Arrays;

@Getter
@RequiredArgsConstructor
public enum ECategoryType {

    POLITICS_CATEGORY("politics"),
    ECONOMY_CATEGORY("economy"),
    LIFE_CATEGORY("life"),
    SCIENCE_CATEGORY("science"),
    ENTERTAIN_CATEGORY("entertain"),
    SPORTS_CATEGORY("sports")
    ;

    private final String value;

    public static ECategoryType fromValue(String value) {
        return Arrays.stream(ECategoryType.values()).
                filter(eCategoryType -> eCategoryType.getValue().equals(value))
                .findFirst()
                .orElseThrow(() -> new CustomException(ErrorCode.INVALID_CATEGORY_ENUM));
    }
}
