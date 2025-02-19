package com.example.appapi.reviews.model;

import lombok.Getter;

public class ReviewsDto {
    @Getter
    public static class Register {
        private String title;
        private String contents;
        private int starPoint;
        private String createdAt;


        public Reviews toEntity() {
            Reviews review = new Reviews(null, title, contents, starPoint, createdAt);
            return review;
        }
    }

}