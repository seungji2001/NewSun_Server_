package com.newsun.Newsun.repository;

import com.newsun.Newsun.domain.Member;
import com.newsun.Newsun.type.ELoginProvider;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;


@Repository
public interface MemberRepository extends JpaRepository<Member, Long> {

    Optional<Member> findBySocialIdAndProvider(String socialId, ELoginProvider provider);
}
