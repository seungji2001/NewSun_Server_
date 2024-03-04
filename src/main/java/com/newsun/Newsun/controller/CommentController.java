package com.newsun.Newsun.controller;

import com.newsun.Newsun.dto.comment.response.CommentListDto;
import com.newsun.Newsun.dto.exception.ResponseDto;
import com.newsun.Newsun.service.CommentService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api")
public class CommentController {

    private final CommentService commentService;

    @GetMapping("/post/{postId}/comment")
    public ResponseDto<CommentListDto> listComment(@PathVariable Long postId) {
        return ResponseDto.ok(commentService.listComment(postId));
    }
}
