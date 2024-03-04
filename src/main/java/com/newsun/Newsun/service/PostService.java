package com.newsun.Newsun.service;


import com.newsun.Newsun.domain.Post;
import com.newsun.Newsun.dto.post.response.PostDto;
import com.newsun.Newsun.dto.post.response.PostListDto;
import com.newsun.Newsun.exception.CustomException;
import com.newsun.Newsun.repository.PostRepository;
import com.newsun.Newsun.type.ECategoryType;
import com.newsun.Newsun.type.ErrorCode;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class PostService {

    private final PostRepository postRepository;

    public PostListDto listPost(ECategoryType categoryType, int page) {
        if (page < 1)
            throw new CustomException(ErrorCode.INVALID_PARAMETER_VALUE);

        Page<Post> postPage = postRepository
                .findAllByCategoryTypeName(categoryType.getValue(), PageRequest.of(page - 1, 5));
        List<PostDto> postList = postPage.getContent().stream()
                .map(post -> PostDto.of(
                        post.getId(),
                        post.getTitle(),
                        post.getContents(),
                        post.getCategoryTypeName(),
                        post.getCreatedAt(),
                        post.getPostLikeList().size()))
                .toList();

        return PostListDto.of(postList);
    }
}
