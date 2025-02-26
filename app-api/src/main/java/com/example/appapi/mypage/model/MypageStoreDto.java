package com.example.appapi.mypage.model;

import com.example.appapi.store.model.Store;
import lombok.Builder;
import lombok.Getter;

public class MypageStoreDto {
    @Builder
    @Getter
    public static class StoreRezRes { // 식당 예약 내역
        private String storeImage;      // 식당 이미지 (이미지 테이블)
        private String storeAddress;    // 식당 주소
        private String storeName;       // 식당 이름
        private String category;        // 식당 카테고리
        private int totalReviews;       // 총 리뷰 수 (식당 리뷰 테이블)
        private String rezTime;         // 예약 시간 (예약 테이블)
        private String rezDate;         // 예약 날짜 (예약 테이블)
        private int rezCount;

        public StoreRezRes from(Store store, ,String storeImage,  ) {
            return Orders.builder()
                    .quantity(quantity)
                    .price(price)
                    .message(message)
                    .status("Pending")
                    .build();
        }
    }
    @Getter
    @Builder
    public static class RezResponse { //식당 예약 내역
        private String storeName;
        private String storeAddress;
        private String category;
        private int totalReviews;
        private String rezTime;
        private String reservation;

        public static OrdersDto.ListResponse from(Store store) {
            return OrdersDto.ListResponse.builder()
                    .idx(orders.getIdx())
                    .quantity(orders.getQuantity())
                    .price(orders.getPrice())
                    .message(orders.getMessage())
                    .status(orders.getStatus())
                    .build();
        }
    }
//    @Getter
//    @Builder
//    public static class LikeResponse {
//        private String storeName;
//        private String storeImage;
//        private String storeAddress;
//        private String callNumber;
//
//        /*
//        store_image: "https://d12zq4w4guyljn.cloudfront.net/20240702062022_photo1_be36f39fdbbe.jpg",
//        store_name: "울프강 스테이크 하우스 ",
//        addresses: "서울 동작구 상도로 12",
//        call_number: "02-861-2389",
//         */
//
//        public static MypageDto.LikeResponse from(Store store) {
//            return LikeResponse.builder()
//                    .storeName(store.getName())
//                    .storeImage(store.getImages())
//                    .storeAddress(store.getAddress())
//                    .callNumber(store.getCallNumber())
//                    .build();
//        }
//    }
}
