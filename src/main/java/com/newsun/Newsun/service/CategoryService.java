package com.newsun.Newsun.service;


import com.newsun.Newsun.domain.Category;
import com.newsun.Newsun.domain.CategoryType;
import com.newsun.Newsun.domain.Member;
import com.newsun.Newsun.dto.category.response.CategoryDto;
import com.newsun.Newsun.dto.category.response.CategoryListDto;
import com.newsun.Newsun.exception.CustomException;
import com.newsun.Newsun.repository.CategoryRepository;
import com.newsun.Newsun.repository.CategoryTypeRepository;
import com.newsun.Newsun.repository.MemberRepository;
import com.newsun.Newsun.type.ErrorCode;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CategoryService {

    private final CategoryRepository categoryRepository;
    private final CategoryTypeRepository categoryTypeRepository;
    private final MemberRepository memberRepository;

    public CategoryListDto getCategoryList() {
        /*
         * 현재 member 확인 TEST 코드
         * 추후 수정 예정
         */
        Member member = memberRepository.findById(1L)
                .orElseThrow(() -> new CustomException(ErrorCode.NOT_FOUND_MEMBER));

        Category category = member.getCategory();
        List<CategoryDto> categoryDtos= category.getCategoryTypes().stream()
                .map(categoryType -> CategoryDto.of(
                        categoryType.getId(),
                        categoryType.getCategoryTypeContents()
                )).toList();

        return CategoryListDto.of(categoryDtos);
    }


}
