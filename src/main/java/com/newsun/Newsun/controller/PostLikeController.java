package com.newsun.Newsun.controller;

import com.newsun.Newsun.dto.exception.ResponseDto;
import com.newsun.Newsun.service.PostLikeService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api")
public class PostLikeController {

    private final PostLikeService postLikeService;

    @PostMapping("/post/{postId}/like")
    public ResponseDto<Boolean> createPostLike(@PathVariable Long postId) {
        return ResponseDto.created(postLikeService.createPostLike(postId));
    }

    @DeleteMapping("/post/{postId}/like")
    public ResponseDto<Boolean> deletePostLike(@PathVariable Long postId) {
        return ResponseDto.ok(postLikeService.deletePostLike(postId));
    }
}
