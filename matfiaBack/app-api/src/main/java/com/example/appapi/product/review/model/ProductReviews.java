package com.example.appapi.product.review.model;

import com.example.appapi.product.model.Products;
import com.example.appapi.product.review.images.model.ProductReviewImages;
import com.example.appapi.users.model.Users;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;

@Entity
@Builder
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "product_reviews")
public class ProductReviews {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idx;

    private int starPoint;
    private String content;
    private LocalDateTime createdAt;

    @ManyToOne
    @JoinColumn(name = "products_idx")
    private Products product;

    @ManyToOne
    @JoinColumn(name = "users_idx")
    private Users user;

    @OneToMany(mappedBy = "productReviews")
    private List<ProductReviewImages> images;
}
