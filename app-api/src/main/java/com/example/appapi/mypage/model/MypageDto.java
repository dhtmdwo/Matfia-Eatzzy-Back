package com.example.appapi.mypage.model;

import com.example.appapi.orders.model.Orders;
import com.example.appapi.orders.model.OrdersDto;
import com.example.appapi.store.model.Store;
import lombok.Builder;
import lombok.Getter;

public class MypageDto {
//    @Builder
//    @Getter
//    public static class RegisterRequest {
//        private int quantity;
//        private int price;
//        private String message;
//        private String status;
//
//        public Orders toEntity() {
//            return Orders.builder()
//                    .quantity(quantity)
//                    .price(price)
//                    .message(message)
//                    .status("Pending")
//                    .build();
//        }
//    }
//    @Getter
//    @Builder
//    public static class RezResponse { //식당 예약 내역
//        private String storeName;
//        private String storeAddress;
//        private String category;
//        private int totalReviews;
//        private String rezTime;
//        private String reservation;
//
//        public static OrdersDto.ListResponse from(Store store) {
//            return OrdersDto.ListResponse.builder()
//                    .idx(orders.getIdx())
//                    .quantity(orders.getQuantity())
//                    .price(orders.getPrice())
//                    .message(orders.getMessage())
//                    .status(orders.getStatus())
//                    .build();
//        }
//    }
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
