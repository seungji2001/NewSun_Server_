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
@Table(name = "category")
public class Category {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "category_id")
    private Long id;

    @Enumerated(EnumType.STRING)
    @Column(name = "category_contents", nullable = false)
    private ECategoryType categoryTypeContents;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_id")
    Member member;

    @Builder
    public Category(
            final ECategoryType categoryTypeContents,
            final Member member
    ) {
        this.categoryTypeContents = categoryTypeContents;
        this.member = member;
    }
}
