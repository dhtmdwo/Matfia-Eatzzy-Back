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
            Reviews reviews = new Reviews(null, title, contents, starPoint, createdAt);
            return reviews;
        }
    }

    @Getter
    public static class Response {
        private Long idx;
        private String title;
        private String contents;
        private int starPoint;
        private String createdAt;

        public static Response from(Reviews review) {
            Response res = new Response();
            res.idx = review.getIdx();
            res.title = review.getTitle();
            res.contents = review.getContents();
            res.starPoint = review.getStarPoint();
            res.createdAt = review.getCreatedAt();
            return res;
        }
    }
}
