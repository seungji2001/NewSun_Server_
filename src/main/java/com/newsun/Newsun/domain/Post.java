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
@Table(name = "post")
public class Post {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "post_id")
    private Long id;

    @Column(name = "post_title", nullable = false)
    private String title;

    @Column(name = "post_contents", length = 1023, nullable = false)
    private String contents;

    @Column(name = "categoryTypeName", nullable = false)
    private String categoryTypeName;

    @Column(name = "category_created_at", nullable = false)
    private LocalDateTime createdAt;

    @Builder
    public Post(final String title,
                final String contents,
                final String categoryTypeName
    ) {
        this.title = title;
        this.contents = contents;
        this.categoryTypeName = categoryTypeName;
        this.createdAt = LocalDateTime.now();
    }
}
