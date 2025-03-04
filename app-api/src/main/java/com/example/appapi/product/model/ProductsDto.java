package com.example.appapi.product.model;

import com.example.appapi.store.review.model.StoreReview;
import com.example.appapi.store.review.model.StoreReviewDto;
import lombok.Builder;
import lombok.Getter;

import java.time.LocalDateTime;
import java.util.List;

public class ProductsDto {

    @Getter
    @Builder
    public static class InfoResponse {
        private String name;
        private String imgPath;
        private double starPoint;
        private int reviewCnt;
        private int price;

        public static InfoResponse fromEntity(Products products) {
            return InfoResponse.builder()
                    .name(products.getName())
                    .imgPath(products.getImages().get(0).getImagePath())
                    .price(products.getPrice())
                    .reviewCnt(products.getReviewCount())
                    .starPoint(products.getStarPoint())
                    .build();
        }
    }


    @Builder
    @Getter
    public static class DetailResponse {
        private String image;
        private double starPoint;
        private int reviewCnt;
        private String productName;
        private int price;
        private String description;

        public static DetailResponse fromEntity(Products products) {
            return DetailResponse.builder()
                    .productName(products.getName())
                    .image(products.getImages().get(0).getImagePath())
                    .price(products.getPrice())
                    .reviewCnt(products.getReviewCount())
                    .starPoint(products.getStarPoint())
                    .description(products.getDescription())
                    .build();
        }
    }

}
