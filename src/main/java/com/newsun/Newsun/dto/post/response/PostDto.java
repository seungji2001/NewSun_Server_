package com.newsun.Newsun.dto.post.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;

import java.io.Serializable;
import java.time.LocalDateTime;

@Builder
public record PostDto (
        @JsonProperty("id")
        Long id,
        @JsonProperty("title")
        String title,
        @JsonProperty("contents")
        String contents,
        @JsonProperty("categoryType")
        String type,
        @JsonProperty("createdAt")
        LocalDateTime createdAt,
        @JsonProperty("likeCount")
        Integer likeCount
) implements Serializable {
    public static PostDto of(
            Long id,
            String title,
            String contents,
            String type,
            LocalDateTime createdAt,
            Integer likeCount
    ) {
        return PostDto.builder()
                .id(id)
                .title(title)
                .contents(contents)
                .type(type)
                .createdAt(createdAt)
                .likeCount(likeCount)
                .build();
        }
}
