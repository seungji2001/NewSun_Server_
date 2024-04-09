package com.newsun.Newsun.dto.DanggnDto;

import lombok.Builder;
import lombok.Data;
import lombok.ToString;

public class DanggnRespoonseDto {

    @Data
    @Builder
    @ToString
    public static class SearchResultDto{
        private String search;
        private String link;
        private String title;
        private String content;
        private String price;
        private String region;
    }
}
