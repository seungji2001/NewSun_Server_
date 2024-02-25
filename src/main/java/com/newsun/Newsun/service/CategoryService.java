package com.newsun.Newsun.service;


import com.newsun.Newsun.domain.Category;
import com.newsun.Newsun.domain.Member;
import com.newsun.Newsun.dto.category.request.CreateCategoryListDto;
import com.newsun.Newsun.dto.category.response.CategoryDto;
import com.newsun.Newsun.dto.category.response.CategoryListDto;
import com.newsun.Newsun.exception.CustomException;
import com.newsun.Newsun.repository.CategoryRepository;
import com.newsun.Newsun.repository.MemberRepository;
import com.newsun.Newsun.type.ECategoryType;
import com.newsun.Newsun.type.ErrorCode;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class CategoryService {

    private final CategoryRepository categoryRepository;
    private final MemberRepository memberRepository;

    public CategoryListDto getCategoryList() {
        /*
         * 현재 member 확인 TEST 코드
         * 추후 수정 예정
         */
        Member member = memberRepository.findById(2L)
                .orElseThrow(() -> new CustomException(ErrorCode.NOT_FOUND_MEMBER));

        List<Category> categoryList = member.getCategoryList();
        List<CategoryDto> categoryDtos = categoryList.stream()
                .map(categoryType -> CategoryDto.of(
                        categoryType.getId(),
                        categoryType.getCategoryTypeContents()
                )).toList();

        return CategoryListDto.of(categoryDtos);
    }

    @Transactional
    public Boolean createCategory(CreateCategoryListDto createCategoryListDto) {
        /*
         * 현재 member 확인 TEST 코드
         * 추후 수정 예정
         */
        Member member = memberRepository.findById(2L)
                .orElseThrow(() -> new CustomException(ErrorCode.NOT_FOUND_MEMBER));

        List<Category> memberCategoryList = member.getCategoryList();
        if (!memberCategoryList.isEmpty()) {
            categoryRepository.deleteAllInBatch(memberCategoryList);
            log.info(memberCategoryList.toString());
//            memberCategoryList.clear();
        }

        log.info(createCategoryListDto.toString());
        List<Category> categoryList = createCategoryListDto.categoryList().stream()
                .map(createCategoryDto ->
                    Category.builder()
                            .member(member)
                            .categoryTypeContents(ECategoryType.fromValue(createCategoryDto.type()))
                            .build()
                ).toList();

        categoryRepository.saveAll(categoryList);

        return Boolean.TRUE;
    }
}
