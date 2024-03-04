package com.newsun.Newsun.controller;


import com.newsun.Newsun.dto.exception.ResponseDto;
import com.newsun.Newsun.dto.post.response.PostListDto;
import com.newsun.Newsun.service.PostService;
import com.newsun.Newsun.type.ECategoryType;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api")
public class PostController {

    private final PostService postService;

    @GetMapping("/post")
    public ResponseDto<PostListDto> listPost(
            @RequestParam(defaultValue = "SCIENCE", name = "category")ECategoryType categoryType,
            @RequestParam(defaultValue = "1") int page) {
        return ResponseDto.ok(postService.listPost(categoryType, page));
    }
}
