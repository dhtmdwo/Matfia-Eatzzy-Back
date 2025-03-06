package com.example.resv.resv.model;

import com.example.appapi.store.model.Store;
import com.example.appapi.users.model.Users;
import io.swagger.v3.oas.annotations.media.Schema;
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
        @Schema(description = "예약 날짜", example = "2025-03-22")
        private String date;
        @Schema(description = "예약 시간", example = "17:30")
        private String time;
        @Schema(description = "예약자 이름", example = "홍길동")
        private String name;
        @Schema(description = "예약 인원", example = "2")
        private int headCount;
        @Schema(description = "요청사항", example = "주차 등록해주세요")
        private String request;
        @Schema(description = "식당 번호", example = "1")
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
        @Schema(description = "예약 번호", example = "1")
        private Long idx;
        @Schema(description = "예약 날짜", example = "2025-03-22")
        private String date;
        @Schema(description = "예약 시간", example = "17:30")
        private String time;
        @Schema(description = "예약자 이름", example = "홍길동")
        private String name;
        @Schema(description = "예약 인원", example = "2")
        private int headCount;
        @Schema(description = "요청사항", example = "주차 등록해주세요")
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
    public static class StoreRezResponse {
        @Schema(description = "예약 번호", example = "1")
        private Long idx;
        @Schema(description = "식당 이미지", example = "image_01.png")
        private String storeImage;
        @Schema(description = "식당 주소", example = "서울 강남구 논현로")
        private String storeAddress;
        @Schema(description = "식당 이름", example = "맛있는 식당")
        private String storeName;
        @Schema(description = "예약 시간", example = "17:30")
        private String time;
        @Schema(description = "예약 날짜", example = "2025-03-22")
        private String date;
        @Schema(description = "예약 인원", example = "2")
        private int headCount;

        public static StoreRezResponse from(Store store, Resv resv) {
            String storeImageUrl = store.getImages().isEmpty() ? null : store.getImages().get(0).getImagePath();

            return StoreRezResponse.builder()
                    .idx(store.getIdx())
                    .storeImage(storeImageUrl)
                    .storeAddress(store.getAddress())
                    .storeName(store.getName())
                    .time(String.valueOf(resv.getTime()))
                    .date(String.valueOf(resv.getDate()))
                    .headCount(resv.getHeadCount())
                    .build();
        }
    }

}