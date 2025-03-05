package com.example.appapi.likes.model;

import com.example.appapi.likes.model.Likes;
import com.example.appapi.likes.model.LikesDto;
import com.example.appapi.reservation.model.Reservation;
import com.example.appapi.store.images.model.StoreImages;
import com.example.appapi.store.model.Store;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;


public class LikesDto {
    @Getter
    public static class LikeRegister {
        private String message;
        public Likes toEntity() {
            return Likes.builder()
                    .build();
        }
    }
    @Getter
    @NoArgsConstructor
    @AllArgsConstructor
    @Builder
    public static class LikesResponse {
        private String message;

        public static LikesDto.LikesResponse from(Likes likes) {
            return LikesDto.LikesResponse.builder()
                    .build();
        }

    }

    @Builder
    @Getter
    public static class StoreLikesResponse {
        private Long idx;               // 좋아요 테이블 idx 값
        private String storeImage;      // 식당 이미지 (이미지 테이블)
        private String storeAddress;    // 식당 주소
        private String storeName;       // 식당 이름
        private String storecallNumber; // 식당 번호

        public static LikesDto.StoreLikesResponse from(Store store) {
            String storeImageUrl = store.getImages().get(0).getImagePath();

            return LikesDto.StoreLikesResponse.builder()
                    .idx(store.getIdx())
                    .storeImage(storeImageUrl)
                    .storeAddress(store.getAddress())
                    .storeName(store.getName())
                    .storecallNumber(store.getCallNumber())
                    .build();
        }
    } // 마이패이지 클라이언트 좋아요 한 식당 내역 보기


}
