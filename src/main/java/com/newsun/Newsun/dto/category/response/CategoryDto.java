package com.newsun.Newsun.dto.category.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.newsun.Newsun.type.ECategoryType;
import lombok.Builder;

import java.io.Serializable;

@Builder
public record CategoryDto(
        @JsonProperty("id")
        Long id,

        @JsonProperty("type")
        String type
) implements Serializable {
    public static CategoryDto of(
            Long categoryId, ECategoryType eCategoryType
    ) {
        return CategoryDto.builder()
                .id(categoryId)
                .type(eCategoryType.getValue())
                .build();
    }
}
