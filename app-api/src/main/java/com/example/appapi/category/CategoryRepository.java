package com.example.appapi.category;

import com.example.appapi.category.model.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface CategoryRepository extends JpaRepository<Category, Long> {
    @Query("SELECT c FROM Category c WHERE c.parentCategory.idx = :parentIdx")
    List<Category> findByParentIdx(Long parentIdx);

    @Query("SELECT c FROM Category c LEFT JOIN FETCH c.childCategoryList WHERE c.idx = :idx")
    Optional<Category> findByIdWithChildren(Long idx);

}
