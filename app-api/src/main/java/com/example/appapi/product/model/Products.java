package com.example.appapi.product.model;

import com.example.appapi.product.images.model.ProductsImages;
import com.example.appapi.product.review.model.ProductReviews;
import com.example.appapi.store.model.Store;
import com.example.appapi.users.model.Users;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity
@Builder
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class Products {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idx;

    private String name;
    private String description;
    private int price;
    private int stock;

    @ManyToOne
    @JoinColumn(name = "users_idx")
    private Users user;

    @ManyToOne
    @JoinColumn(name = "store_idx")
    private Store store;

    @OneToMany(mappedBy = "products")
    private List<ProductsImages> images;

    @OneToMany(mappedBy = "products")
    private List<ProductReviews> reviews;
}
