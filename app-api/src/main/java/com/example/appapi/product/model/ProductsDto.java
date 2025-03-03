package com.example.appapi.product.model;

import lombok.Builder;
import lombok.Getter;

public class ProductsDto {

    @Getter
    @Builder
    public static class InfoResponse {
        private Long idx;
        private String name;
        private String imgPath;
        private double starPoint;
        private int reviewCnt;
        private int price;

        public static InfoResponse fromEntity(Products products) {
            return InfoResponse.builder()
                    .idx(products.getIdx())
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
