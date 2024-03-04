package com.newsun.Newsun.dto.comment.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;

import java.io.Serializable;

@Builder
public record CommentDto(
        @JsonProperty("id")
        Long id,
        @JsonProperty("memberId")
        Long memberId,
        @JsonProperty("contents")
        String contents,
        @JsonProperty("createdAt")
        String createdAt,
        @JsonProperty("updatedAt")
        String updatedAt,
        @JsonProperty("likeCount")
        Integer likeCount,
        @JsonProperty("isLike")
        Boolean isLike
) implements Serializable {
    public static CommentDto of(
            Long id,
            Long memberId,
            String contents,
            String createdAt,
            String updatedAt,
            Integer likeCount,
            Boolean isLike
    ) {
        return CommentDto.builder()
                .id(id)
                .memberId(memberId)
                .contents(contents)
                .createdAt(createdAt)
                .updatedAt(updatedAt)
                .likeCount(likeCount)
                .isLike(isLike)
                .build();
    }
}
