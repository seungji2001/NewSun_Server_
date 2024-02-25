package com.newsun.Newsun.dto.category.request;

import com.fasterxml.jackson.annotation.JsonProperty;

public record CreateCategoryDto(
        @JsonProperty("type")
        String type
) {
}
