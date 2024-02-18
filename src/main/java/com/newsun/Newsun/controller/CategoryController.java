package com.newsun.Newsun.controller;


import com.newsun.Newsun.dto.category.response.CategoryListDto;
import com.newsun.Newsun.dto.exception.ResponseDto;
import com.newsun.Newsun.service.CategoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api")
public class CategoryController {

    private final CategoryService categoryService;

    @GetMapping("/category")
    public ResponseDto<CategoryListDto> listCategory() {
        return ResponseDto.ok(categoryService.getCategoryList());
    }
}
