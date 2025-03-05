package com.example.appapi.product.model;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

public class ProductsDto {

    @Getter
    @Builder
    public static class Create{
        private String name;
        private String description;
        private int price;
        private int stock;
        private List<String> imagePaths;

        public Products toEntity() {
            return Products.builder()
                    .name(name)
                    .description(description)
                    .price(price)
                    .stock(stock)
                    .build();
        }
    }

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


    @Getter
    @Builder
    public static class ProductRes {
        private Long idx;
        private String name;
        private int price;
        @Setter
        private List<String> imageUrls;

        public static ProductRes of(Products entity) {
            return ProductRes.builder()
                    .idx(entity.getIdx())
                    .name(entity.getName())
                    .price(entity.getPrice())
                    .imageUrls(
                            entity.getImages() == null ? List.of() : entity.getImages().stream()
                                    .map(image -> image.getImagePath()).toList()
                    )
                    .build();
        }
    }

}
