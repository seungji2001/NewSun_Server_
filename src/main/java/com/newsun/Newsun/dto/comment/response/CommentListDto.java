package com.newsun.Newsun.dto.comment.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;

import java.io.Serializable;
import java.util.List;

@Builder
public record CommentListDto(
        @JsonProperty("commentList")
        List<CommentDto> commentList
) implements Serializable {
    public static CommentListDto of(List<CommentDto> commentList) {
        return CommentListDto.builder()
                .commentList(commentList)
                .build();
    }
}
