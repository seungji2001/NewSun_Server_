package com.newsun.Newsun.type;

import lombok.Getter;

@Getter
public enum ECategoryType {

    POLITICS_CATEGORY("politics"),
    ECONOMY_CATEGORY("economy"),
    LIFE_CATEGORY("life"),
    SCIENCE_CATEGORY("science"),
    ENTERTAIN_CATEGORY("entertain"),
    SPORTS_CATEGORY("sports")

    ;
    private final String value;
    ECategoryType(String value) {
        this.value = value;
    }

}
