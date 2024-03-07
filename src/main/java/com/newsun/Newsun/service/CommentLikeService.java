package com.newsun.Newsun.service;

import com.newsun.Newsun.domain.Comment;
import com.newsun.Newsun.domain.CommentLike;
import com.newsun.Newsun.domain.Member;
import com.newsun.Newsun.exception.CustomException;
import com.newsun.Newsun.repository.CommentLikeRepository;
import com.newsun.Newsun.repository.CommentRepository;
import com.newsun.Newsun.repository.MemberRepository;
import com.newsun.Newsun.type.ErrorCode;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@Service
@RequiredArgsConstructor
public class CommentLikeService {

    private final MemberRepository memberRepository;
    private final CommentRepository commentRepository;
    private final CommentLikeRepository commentLikeRepository;

    @Transactional
    public Boolean createLikeComment(Long commentId) {
        /*
         * 현재 member 확인 TEST 코드
         * 추후 수정 예정
         */
        Member member = memberRepository.findById(2L)
                .orElseThrow(() -> new CustomException(ErrorCode.NOT_FOUND_MEMBER));

        Comment comment = commentRepository.findById(commentId)
                .orElseThrow(() -> new CustomException(ErrorCode.NOT_FOUND_COMMENT));

        CommentLike commentLike = CommentLike.builder()
                                            .member(member)
                                            .comment(comment)
                                            .build();

        commentLikeRepository.save(commentLike);

        return Boolean.TRUE;
    }

    @Transactional
    public Boolean deleteLikeComment(Long commentId) {
        /*
         * 현재 member 확인 TEST 코드
         * 추후 수정 예정
         */
        Member member = memberRepository.findById(2L)
                .orElseThrow(() -> new CustomException(ErrorCode.NOT_FOUND_MEMBER));

        CommentLike commentLike = commentLikeRepository.findByCommentIdAndMember(commentId, member)
                .orElseThrow(() -> new CustomException((ErrorCode.NOT_FOUND_COMMENTLIKE)));

        commentLikeRepository.delete(commentLike);

        return Boolean.TRUE;
    }
}
