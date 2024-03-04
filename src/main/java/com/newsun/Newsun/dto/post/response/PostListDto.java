package com.newsun.Newsun.dto.post.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;

import java.io.Serializable;
import java.util.List;

@Builder
public record PostListDto(
        @JsonProperty("postList")
        List<PostDto> postList
) implements Serializable {
    public static PostListDto of(List<PostDto> postList) {
        return PostListDto.builder()
                .postList(postList)
                .build();
    }
}
