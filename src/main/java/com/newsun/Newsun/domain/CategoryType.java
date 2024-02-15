package com.newsun.Newsun.domain;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Table(name = "category_type")
public class CategoryType {

    @Id
    @Column(name = "category_type_id")
    private Long id;

    @Column(name = "contents", nullable = false)
    private String contents;

    @Builder
    public CategoryType(final String contents) {
        this.contents = contents;
    }
}
