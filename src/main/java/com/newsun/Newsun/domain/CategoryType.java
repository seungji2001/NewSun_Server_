package com.newsun.Newsun.domain;

import com.newsun.Newsun.type.ECategoryType;
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

    @Enumerated(EnumType.STRING)
    @Column(name = "category_type_contents")
    private ECategoryType categoryTypeContents;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "category_id")
    private Category category;

    @Builder
    public CategoryType(
            final ECategoryType categoryTypeContents,
            final Category category
    ) {
        this.categoryTypeContents = categoryTypeContents;
        this.category = category;
    }
}
