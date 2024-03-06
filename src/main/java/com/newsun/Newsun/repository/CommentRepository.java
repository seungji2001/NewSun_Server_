package com.newsun.Newsun.repository;

import com.newsun.Newsun.domain.Comment;
import com.newsun.Newsun.domain.Member;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface CommentRepository extends JpaRepository<Comment, Long> {
    List<Comment> findAllByPostId(Long postId);
    Optional<Comment> findByIdAndMember(Long commentId, Member member);
}
