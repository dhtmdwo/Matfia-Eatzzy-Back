package com.example.appapi.product.review.repository;

import com.example.appapi.product.review.model.ProductReviews;
import com.example.appapi.store.review.model.StoreReview;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface ProductReviewsRepository extends JpaRepository<ProductReviews, Long> {
    Optional<ProductReviews> findByIdx(Long idx);

    @Query("SELECT DISTINCT r FROM ProductReviews r " +
            "LEFT JOIN FETCH r.images s " +
            "WHERE r.user.idx = :userId")
    List<ProductReviews> findReviewBy(@Param("userId") Long userId); //내가 작성한 상품 리뷰 보기
}
