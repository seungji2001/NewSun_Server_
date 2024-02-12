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

    @Column(name = "title", nullable = false)
    private String title;

    @Column(name = "contents", length = 1023, nullable = false)
    private String contents;

    @Column(name = "category_type_name", nullable = false)
    private String categoryTypeName;

    @Column(name = "created_at", nullable = false)
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
