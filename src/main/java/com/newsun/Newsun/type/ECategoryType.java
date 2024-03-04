package com.newsun.Newsun.type;

import com.newsun.Newsun.exception.CustomException;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.util.Arrays;

@Getter
@RequiredArgsConstructor
public enum ECategoryType {

    POLITICS("POLITICS"),
    ECONOMY("ECONOMY"),
    LIFE("LIFE"),
    SCIENCE("SCIENCE"),
    ENTERTAIN("ENTERTAIN"),
    SPORTS("SPORTS")
    ;

    private final String value;

    public static ECategoryType fromValue(String value) {
        return Arrays.stream(ECategoryType.values()).
                filter(eCategoryType -> eCategoryType.getValue().equals(value))
                .findFirst()
                .orElseThrow(() -> new CustomException(ErrorCode.INVALID_CATEGORY_ENUM));
    }
}
