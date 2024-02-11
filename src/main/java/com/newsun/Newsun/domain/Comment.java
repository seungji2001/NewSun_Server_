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
@Table(name = "comment")
public class Comment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "comment_id")
    private Long id;

    @Column(name = "commment_contents", length = 511, nullable = false)
    private String contents;

    @Column(name = "comment_created_at", nullable = false)
    private LocalDateTime createdAt;

    @Column(name = "comment_update_at")
    private LocalDateTime updatedAt;

    @ManyToOne(fetch = FetchType.LAZY)
    private Member member;

    @Builder
    public Comment(final String contents,
                   final Member member
    ) {
        this.contents = contents;
        this.createdAt = LocalDateTime.now();
        this.member = member;
    }

}
