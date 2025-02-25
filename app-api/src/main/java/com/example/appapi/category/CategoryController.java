package com.example.appapi.category;

import com.example.appapi.category.model.Category;
import com.example.appapi.category.model.CategoryDto;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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

    @GetMapping("/list")
    public ResponseEntity<List<CategoryDto.CategoryResponseDto>> getBigCategory() {
        List<CategoryDto.CategoryResponseDto> bigCategoryList = categoryService.getBigCategory();

        return ResponseEntity.ok(bigCategoryList);
    }
}
