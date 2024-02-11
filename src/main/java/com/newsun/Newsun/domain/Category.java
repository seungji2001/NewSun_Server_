package com.newsun.Newsun.domain;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Table(name = "category")
public class Category {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "category_id")
    private Long id;

    @OneToMany
    @JoinColumn(name = "category_id")
    private List<CategoryType> categoryTypes = new ArrayList<>();

    @Builder
    public Category(List<CategoryType> categoryTypes) {
        this.categoryTypes = categoryTypes;
    }
}
