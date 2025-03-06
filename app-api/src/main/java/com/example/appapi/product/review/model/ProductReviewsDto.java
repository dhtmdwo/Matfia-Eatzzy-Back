package com.example.appapi.product.review.model;

import com.example.appapi.product.model.Products;
import com.example.appapi.store.model.Store;
import com.example.appapi.product.model.ProductsDto;
import com.example.appapi.product.review.images.model.ProductReviewImages;
import com.example.appapi.store.review.model.StoreReview;
import com.example.appapi.store.review.model.StoreReviewDto;
import com.example.appapi.users.model.Users;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

public class ProductReviewsDto {

    @Getter
    @Builder
    public static class InfoResponse{
        private int starPoint;
        private String content;
        private LocalDateTime createdAt;
        private List<String> imageUrls;

        public static InfoResponse fromEntity(ProductReviews reviews) {
            return InfoResponse.builder()
                    .starPoint(reviews.getStarPoint())
                    .content(reviews.getContent())
                    .createdAt(reviews.getCreatedAt())
                    .imageUrls(reviews.getImages().stream().map(ProductReviewImages::getImagePath).collect(Collectors.toList()))
                    .build();
        }
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

    @Getter
    public static class CreateReq {
        private String title;
        private String content;
        private int starPoint;
        private LocalDateTime createdAt;
        private Long productIdx;
        private List<String> imageUrls;

        public ProductReviews toEntity(Products products) {
            return ProductReviews.builder()
                    .title(title)
                    .content(content)
                    .starPoint(starPoint)
                    .createdAt(createdAt)
                    .products(products)
                    .build();
        }
    } // 리뷰 작성용 dto

    @Getter
    @Builder
    @Setter
    public static class ReviewRes {
        private Long idx;
        private String title;
        private String content;
        private int starPoint;
        private LocalDateTime createdAt;
        private List<String> imageUrls;
        private Long productIdx;

        public static ProductReviewsDto.ReviewRes of(ProductReviews entity, List<String> imageUrls) {
            return ProductReviewsDto.ReviewRes.builder()
                    .idx(entity.getIdx())
                    .title(entity.getTitle())
                    .content(entity.getContent())
                    .starPoint(entity.getStarPoint())
                    .createdAt(entity.getCreatedAt())
                    .imageUrls(imageUrls)
                    .productIdx(entity.getIdx())
                    .build();
        }
    }

    @Builder
    public static class RegisterRequest {
        private Long productIdx;
        private String title;
        private String content;
        private String image;
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
