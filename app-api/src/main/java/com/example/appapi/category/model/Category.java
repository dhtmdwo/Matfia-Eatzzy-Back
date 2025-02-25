package com.example.appapi.category.model;

import com.example.appapi.store.model.Store;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
@Entity
public class Category {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idx;
    private String name;

    // 자기 참조 관계 (부모 카테고리)
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "parent_idx", nullable = true)
    private Category parentCategory;

    // 자식 카테고리 목록
    @OneToMany(mappedBy = "parentCategory")
    private List<Category> childCategoryList  = new ArrayList<>();


    @OneToMany(mappedBy = "category")
    private List<Store> storeList = new ArrayList<>();
}