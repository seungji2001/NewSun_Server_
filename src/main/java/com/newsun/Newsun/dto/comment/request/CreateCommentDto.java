package com.newsun.Newsun.dto.comment.request;

import com.fasterxml.jackson.annotation.JsonProperty;

public record CreateCommentDto(
        @JsonProperty("contents")
        String contents
) {
}
