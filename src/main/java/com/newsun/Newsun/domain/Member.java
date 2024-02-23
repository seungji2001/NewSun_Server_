package com.newsun.Newsun.domain;

import com.newsun.Newsun.type.ELoginProvider;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Table(name = "member")
public class Member {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "member_id")
    private Long id;

    @Column(name = "password", nullable = false)
    private String password;

    @Column(name = "social_id", unique = true)
    private String socialId;

    @Column(name = "provider")
    @Enumerated(EnumType.STRING)
    private ELoginProvider provider;

    @Column(name = "is_login", columnDefinition = "TINYINT(1)", nullable = false)
    private Boolean isLogin;

    @Column(name = "refresh_token")
    private String refreshToken;

    @Column(name = "created_at", nullable = false)
    private LocalDateTime createdAt;

    @OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "category_id", referencedColumnName = "category_id", nullable = false)
    private Category category;

    @OneToMany(mappedBy = "member", cascade = CascadeType.ALL)
    private List<PostLike> postLikes = new ArrayList<>();

    @OneToMany(mappedBy = "member", cascade = CascadeType.ALL)
    private List<Comment> comments = new ArrayList<>();

    @OneToMany(mappedBy = "member", cascade = CascadeType.ALL)
    private List<CommentLike> commentLikes = new ArrayList<>();

    @Builder
    public Member(final String socialId,
                  final String password,
                  final ELoginProvider provider,
                  final Category category
    ) {
        this.socialId = socialId;
        this.password = password;
        this.provider = provider;
        this.isLogin = false;
        this.refreshToken = null;
        this.createdAt = LocalDateTime.now();
        this.category = category;
    }
}
