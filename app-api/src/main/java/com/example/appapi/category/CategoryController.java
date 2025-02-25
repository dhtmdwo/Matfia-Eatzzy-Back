package com.example.appapi.category;

import com.example.appapi.category.model.Category;
import com.example.appapi.category.model.CategoryDto;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/app/category")
public class CategoryController {
    private final CategoryService categoryService;

    @PostMapping("/create")
    public ResponseEntity<CategoryDto.CategoryResponseDto> create(@RequestBody CategoryDto.CreateCategoryDto dto) {
        CategoryDto.CategoryResponseDto category = categoryService.create(dto);
        return ResponseEntity.ok(category);
    }
}
