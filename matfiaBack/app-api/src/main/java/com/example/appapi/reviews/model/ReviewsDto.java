package com.example.appapi.reviews.model;

public class ReviewsDto {
    @Getter
    public static class Register {
        private String title;
        private String contents;
        private int starPoint;
        private String createdAt;


        public Review toEntity() {
            Review review = new Review(null, title, contents, starPoint, createdAt);
            return review;
        }
    }

}