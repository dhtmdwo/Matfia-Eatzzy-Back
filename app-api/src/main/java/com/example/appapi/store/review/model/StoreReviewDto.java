package com.example.appapi.store.review.model;

import com.example.appapi.store.model.Store;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.List;

public class StoreReviewDto {

    @Getter
    public static class CreateReq {
        private String title;
        private String contents;
        private int starPoint;
        private LocalDateTime createdAt;
        private Long storeIdx;

        public StoreReview toEntity(Store store) {
            return StoreReview.builder()
                    .title(title)
                    .contents(contents)
                    .starPoint(starPoint)
                    .createdAt(createdAt)
                    .store(store)
                    .build();
        }
    }

    @Getter
    @Builder
    @Setter
    public static class ReviewRes {
        private Long idx;
        private String title;
        private String contents;
        private int starPoint;
        private LocalDateTime createdAt;
        private List<String> imageUrls;
        private Long storeIdx;

        public static ReviewRes of(StoreReview entity, List<String> imageUrls) {
            return ReviewRes.builder()
                    .idx(entity.getIdx())
                    .title(entity.getTitle())
                    .contents(entity.getContents())
                    .starPoint(entity.getStarPoint())
                    .createdAt(entity.getCreatedAt())
                    .imageUrls(imageUrls)
                    .storeIdx(entity.getIdx())
                    .build();
        }
    }

    @Builder
    @Getter
    public static class StoreReivewResponse {
        private Long idx;          // 테이블 idx
        private String contents; // 내용
        private int starPoint; // 별점
        private List<String> reviewImage; // 리뷰 이미지
        private LocalDateTime createdAt; // 작성 날짜
        
        public static StoreReviewDto.StoreReivewResponse from(StoreReview storeReview, List<String> imageUrls) {

            return StoreReviewDto.StoreReivewResponse.builder()
                    .idx(storeReview.getIdx())
                    .contents(storeReview.getContents())
                    .starPoint(storeReview.getStarPoint())
                    .reviewImage(imageUrls)
                    .createdAt(storeReview.getCreatedAt())
                    .build();
        }
    } // 마이페이지 클라이언트 내가 작성한 식당 리뷰 보기
}
