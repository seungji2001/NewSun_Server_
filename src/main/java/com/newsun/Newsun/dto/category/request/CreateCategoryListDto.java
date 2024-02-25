package com.newsun.Newsun.dto.category.request;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

public record CreateCategoryListDto(
        @JsonProperty("categoryList")
        List<CreateCategoryDto> categoryList
) {
}
