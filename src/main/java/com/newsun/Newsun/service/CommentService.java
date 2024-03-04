package com.newsun.Newsun.service;

import com.newsun.Newsun.domain.Comment;
import com.newsun.Newsun.domain.Member;
import com.newsun.Newsun.dto.comment.response.CommentDto;
import com.newsun.Newsun.dto.comment.response.CommentListDto;
import com.newsun.Newsun.exception.CustomException;
import com.newsun.Newsun.repository.CommentLikeRepository;
import com.newsun.Newsun.repository.CommentRepository;
import com.newsun.Newsun.repository.MemberRepository;
import com.newsun.Newsun.type.ErrorCode;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class CommentService {

    private final CommentRepository commentRepository;
    private final CommentLikeRepository commentLikeRepository;
    private final MemberRepository memberRepository;

    public CommentListDto listComment(Long postId) {
        /*
         * 현재 member 확인 TEST 코드
         * 추후 수정 예정
         */
        Member member = memberRepository.findById(2L)
                .orElseThrow(() -> new CustomException(ErrorCode.NOT_FOUND_MEMBER));

        List<Comment> commentList = commentRepository.findAllByPostId(postId);
        List<CommentDto> commentDtos = commentList.stream()
                .map(comment ->
                        CommentDto.of(
                                comment.getId(),
                                comment.getMember().getId(),
                                comment.getContents(),
                                comment.getCreatedAt().toString(),
                                comment.getUpdatedAt() != null ? comment.getUpdatedAt().toString() : null,
                                commentLikeRepository.countByComment(comment),
                                commentLikeRepository.existsByMember(member)
                        )
                 ).toList();
         return CommentListDto.of(commentDtos);
    }
}
