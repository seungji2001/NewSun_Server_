package com.newsun.Newsun.dto.category.response;


import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;

import java.io.Serializable;
import java.util.List;

@Builder
public record CategoryListDto(
        @JsonProperty("categoryList")
        List<CategoryDto> categoryList

) implements Serializable {
    public static CategoryListDto of(List<CategoryDto> categoryList) {
        return CategoryListDto.builder()
                .categoryList(categoryList)
                .build();
    }
}
