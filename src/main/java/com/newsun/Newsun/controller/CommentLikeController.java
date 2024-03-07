package com.newsun.Newsun.controller;

import com.newsun.Newsun.dto.exception.ResponseDto;
import com.newsun.Newsun.service.CommentLikeService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api")
public class CommentLikeController {

    private final CommentLikeService commentLikeService;
    @PostMapping("/comment/{commentId}/like")
    public ResponseDto<Boolean> createLikeComment(@PathVariable Long commentId) {
        return ResponseDto.created(commentLikeService.createLikeComment(commentId));
    }

    @DeleteMapping("/comment/{commentId}/like")
    public ResponseDto<Boolean> deleteLikeComment(@PathVariable Long commentId) {
        return ResponseDto.ok(commentLikeService.deleteLikeComment(commentId));
    }
}
