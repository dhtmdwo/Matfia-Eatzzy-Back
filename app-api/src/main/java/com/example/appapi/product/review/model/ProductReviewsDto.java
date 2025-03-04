package com.example.appapi.product.review.model;

import com.example.appapi.store.review.model.StoreReview;
import com.example.appapi.store.review.model.StoreReviewDto;
import lombok.Builder;
import lombok.Getter;

import java.time.LocalDateTime;
import java.util.List;

public class ProductReviewsDto {

    public static class InfoResponse{

    }

    @Builder
    @Getter
    public static class ProductReivewResponse {
        private String contents; // 내용
        private int starPoint; // 별점
        private List<String> reviewImage; // 리뷰 이미지
        private LocalDateTime createdAt; // 작성 날짜

        public static ProductReviewsDto.ProductReivewResponse from(ProductReviews productReviews, List<String> imageUrls) {

            return ProductReviewsDto.ProductReivewResponse.builder()
                    .contents(productReviews.getContent())
                    .starPoint(productReviews.getStarPoint())
                    .reviewImage(imageUrls)
                    .createdAt(productReviews.getCreatedAt())
                    .build();
        }
    } // 마이페이지 클라이언트 내가 작성한 상품 리뷰 보기

}
