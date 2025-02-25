package com.example.appapi.product.review.repository;

import com.example.appapi.product.review.model.ProductReviews;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ProductReviewsRepository extends JpaRepository<ProductReviews, Long> {
    Optional<ProductReviews> findByIdx(Long idx);
}
