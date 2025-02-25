package com.example.appapi.category;

import com.example.appapi.category.model.Category;
import com.example.appapi.category.model.CategoryDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Service
public class CategoryService {
    private final CategoryRepository categoryRepository;

    @Transactional
    public CategoryDto.CategoryResponseDto create(CategoryDto.CreateCategoryDto dto) {
        Category parentCategory = null;
        if (dto.getParentIdx() != null) {
            parentCategory = categoryRepository.findById(dto.getParentIdx())
                    .orElseThrow(() -> new IllegalArgumentException("부모 카테고리를 찾을 수 없습니다. ID: " + dto.getParentIdx()));
        }

        Category category = categoryRepository.save(dto.toEntity(parentCategory));

        return CategoryDto.CategoryResponseDto.from(category);
    }
}
