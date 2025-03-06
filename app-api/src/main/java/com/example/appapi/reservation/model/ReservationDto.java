package com.example.appapi.reservation.model;

import com.example.appapi.store.model.Store;
import lombok.Builder;
import lombok.Getter;

public class ReservationDto {
    @Builder
    @Getter
    public static class StoreRezResponse { // 마이페이지 식당 예약 내역
        private Long idx;               // 테이블 idx 값
        private String storeImage;      // 식당 이미지 (이미지 테이블)
        private String storeAddress;    // 식당 주소
        private String storeName;       // 식당 이름
        private String rezTime;         // 예약 시간 (예약 테이블)
        private String rezDate;         // 예약 날짜 (예약 테이블)
        private int rezCount;           // 예약 인원 (예약 테이블)

        // 카테고리 안써서 뺏다
        public static ReservationDto.StoreRezResponse from(Store store, Reservation reservation) {
            String storeImageUrl = store.getImages().get(0).getImagePath();

            return ReservationDto.StoreRezResponse.builder()
                    .idx(store.getIdx())
                    .storeImage(storeImageUrl)
                    .storeAddress(store.getAddress())
                    .storeName(store.getName())
                    .rezTime(reservation.getTime())
                    .rezDate(reservation.getDate())
                    .rezCount(reservation.getHeadCount())
                    .build();
        }
    }
}
