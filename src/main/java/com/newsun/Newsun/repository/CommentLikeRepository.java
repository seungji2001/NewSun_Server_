package com.newsun.Newsun.repository;

import com.newsun.Newsun.domain.Comment;
import com.newsun.Newsun.domain.CommentLike;
import com.newsun.Newsun.domain.Member;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CommentLikeRepository extends JpaRepository<CommentLike, Long> {
    Integer countByComment(Comment comment);
    Boolean existsByMember(Member member);
}
