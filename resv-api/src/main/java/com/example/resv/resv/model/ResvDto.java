package com.example.resv.resv.model;

import com.example.appapi.reservation.model.Reservation;
import com.example.appapi.reservation.model.ReservationDto;
import com.example.appapi.store.model.Store;
import com.example.appapi.users.model.Users;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

public class ResvDto {

    @Getter
    public static class CreateResvRequest {
        private String date;
        private String time;
        private String name;
        private int headCount;
        private String request;
        private Long storeIdx;

        public Resv toEntity(Users user, Store store) {
            return Resv.builder()
                    .date(LocalDate.parse(this.date, DateTimeFormatter.ofPattern("yyyy-MM-dd")))
                    .time(LocalTime.parse(this.time, DateTimeFormatter.ofPattern("HH:mm")))
                    .name(this.name)
                    .headCount(this.headCount)
                    .request(this.request)
                    .store(store)
                    .user(user)
                    .build();
        }
    }

    @Getter
    @Builder
    @NoArgsConstructor
    @AllArgsConstructor
    public static class ResvResponse {
        private Long idx;
        private String date;
        private String time;
        private String name;
        private int headCount;
        private String request;

        public static ResvResponse from(Resv entity) {
            return ResvResponse.builder()
                    .idx(entity.getIdx())
                    .date(String.valueOf(entity.getDate()))
                    .time(String.valueOf(entity.getTime()))
                    .name(entity.getName())
                    .headCount(entity.getHeadCount())
                    .request(entity.getRequest())
                    .build();
        }

    }

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
        public static StoreRezResponse from(Store store, Resv resv) {
            String storeImageUrl = store.getImages().get(0).getImagePath();

            return StoreRezResponse.builder()
                    .idx(store.getIdx())
                    .storeImage(storeImageUrl)
                    .storeAddress(store.getAddress())
                    .storeName(store.getName())
                    .rezTime(String.valueOf(resv.getTime()))
                    .rezDate(String.valueOf(resv.getDate()))
                    .rezCount(resv.getHeadCount())
                    .build();
        }
    }

}