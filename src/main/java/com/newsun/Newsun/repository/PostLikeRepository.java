package com.newsun.Newsun.repository;

import com.newsun.Newsun.domain.Member;
import com.newsun.Newsun.domain.Post;
import com.newsun.Newsun.domain.PostLike;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface PostLikeRepository extends JpaRepository<PostLike, Long> {
    Optional<PostLike> findByPostAndMember(Post post, Member member);
}
