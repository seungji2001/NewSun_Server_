package com.newsun.Newsun.domain;


import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Table(name = "comment_like")
public class CommentLike {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "category_like_id")
    private Long id;

    @Column(name = "comment_like_created_at")
    private LocalDateTime createdAt;

    @ManyToOne(fetch = FetchType.LAZY)
    @Column(name = "comment_like_member")
    private Member member;

    @ManyToOne(fetch = FetchType.LAZY)
    @Column(name = "comment_like_comment")
    private Comment comment;

    @Builder
    public CommentLike(final Member member,
                       final Comment comment
    ) {
        this.createdAt = LocalDateTime.now();
        this.member = member;
        this.comment = comment;
    }
}
