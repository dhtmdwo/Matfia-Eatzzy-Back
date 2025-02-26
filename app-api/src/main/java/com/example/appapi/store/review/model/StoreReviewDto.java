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
}
