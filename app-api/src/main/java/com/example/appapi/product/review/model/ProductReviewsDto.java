package com.example.appapi.product.review.model;

import com.example.appapi.product.model.Products;
import com.example.appapi.product.model.ProductsDto;
import com.example.appapi.product.review.images.model.ProductReviewImages;
import com.example.appapi.store.review.model.StoreReview;
import com.example.appapi.store.review.model.StoreReviewDto;
import com.example.appapi.users.model.Users;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;
import lombok.Getter;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

public class ProductReviewsDto {

    @Getter
    @Builder
    public static class InfoResponse {
        @Schema(description = "리뷰의 별점", example = "5")
        private int starPoint;

        @Schema(description = "리뷰 내용", example = "이 제품은 정말 훌륭합니다!")
        private String content;

        @Schema(description = "리뷰 작성 날짜 및 시간", example = "2025-03-06T09:26:58")
        private LocalDateTime createdAt;

        @Schema(description = "리뷰 이미지 URL 목록")
        private List<String> imageUrls;

        public static InfoResponse fromEntity(ProductReviews reviews) {
            return InfoResponse.builder()
                    .starPoint(reviews.getStarPoint())
                    .content(reviews.getContent())
                    .createdAt(reviews.getCreatedAt())
                    .imageUrls(reviews.getImages().stream()
                            .map(ProductReviewImages::getImagePath)
                            .collect(Collectors.toList()))
                    .build();
        }
    }

    @Getter
    @Builder
    public static class ProductReivewResponse {
        @Schema(description = "리뷰 내용", example = "이 제품은 정말 훌륭합니다!")
        private String contents;

        @Schema(description = "리뷰의 별점", example = "5")
        private int starPoint;

        @Schema(description = "리뷰 이미지 URL 목록")
        private List<String> reviewImage;

        @Schema(description = "리뷰 작성 날짜 및 시간", example = "2025-03-06T09:26:58")
        private LocalDateTime createdAt;

        public static ProductReivewResponse from(ProductReviews productReviews, List<String> imageUrls) {
            return ProductReivewResponse.builder()
                    .contents(productReviews.getContent())
                    .starPoint(productReviews.getStarPoint())
                    .reviewImage(imageUrls)
                    .createdAt(productReviews.getCreatedAt())
                    .build();
        }
    }

    @Getter
    @Builder
    public static class RegisterRequest {
        @Schema(description = "상품의 고유 식별자", example = "1")
        private Long productIdx;

        @Schema(description = "리뷰 제목", example = "최고의 제품!")
        private String title;

        @Schema(description = "리뷰 내용", example = "이 제품은 제 기대를 완전히 충족시켰습니다.")
        private String content;

        @Schema(description = "리뷰 이미지 URL", example = "/images/review1.jpg")
        private String image;

        @Schema(description = "리뷰의 별점", example = "5")
        private int starPoint;

        public ProductReviews toEntity(Users users) {
            return ProductReviews.builder()
                    .user(users)
                    .products(Products.builder()
                            .idx(productIdx)
                            .build())
                    .content(content)
                    .title(title)
                    .starPoint(starPoint)
                    .build();
        }
    }
}

