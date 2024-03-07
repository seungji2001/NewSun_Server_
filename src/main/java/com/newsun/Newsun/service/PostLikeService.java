package com.newsun.Newsun.service;

import com.newsun.Newsun.domain.Member;
import com.newsun.Newsun.domain.Post;
import com.newsun.Newsun.domain.PostLike;
import com.newsun.Newsun.exception.CustomException;
import com.newsun.Newsun.repository.MemberRepository;
import com.newsun.Newsun.repository.PostLikeRepository;
import com.newsun.Newsun.repository.PostRepository;
import com.newsun.Newsun.type.ErrorCode;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class PostLikeService {

    private final MemberRepository memberRepository;
    private final PostRepository postRepository;
    private final PostLikeRepository postLikeRepository;

    public Boolean createPostLike(Long postId) {
        /*
         * 현재 member 확인 TEST 코드
         * 추후 수정 예정
         */
        Member member = memberRepository.findById(2L)
                .orElseThrow(() -> new CustomException(ErrorCode.NOT_FOUND_MEMBER));

        Post post = postRepository.findById(postId)
                .orElseThrow(() -> new CustomException((ErrorCode.NOT_FOUND_POST)));

        PostLike postLike = PostLike.builder()
                                    .member(member)
                                    .post(post)
                                    .build();

        postLikeRepository.save(postLike);

        return Boolean.TRUE;
    }

    public Boolean deletePostLike(Long postId) {
        /*
         * 현재 member 확인 TEST 코드
         * 추후 수정 예정
         */
        Member member = memberRepository.findById(2L)
                .orElseThrow(() -> new CustomException(ErrorCode.NOT_FOUND_MEMBER));

        Post post = postRepository.findById(postId)
                .orElseThrow(() -> new CustomException(ErrorCode.NOT_FOUND_POST));

        PostLike postLike = postLikeRepository.findByPostAndMember(post, member)
                .orElseThrow(() -> new CustomException(ErrorCode.NOT_FOUND_POSTLIKE));

        postLikeRepository.delete(postLike);

        return Boolean.TRUE;
    }
}
