package com.example.appapi.store.model;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.DayOfWeek;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class StoreDto {

    // store 생성 request
    @Getter
    public static class CreateStoreRequestDto {
        @NotBlank
        private String name;
        private String description;
        private String callNumber;
        private LocalTime startTime;
        private LocalTime endTime;
        @NotBlank
        private String openingHours;
        @NotBlank
        private String address;
        private String shortAddress;
        private Long categoryIdx;
        private Long userIdx;

        List<ClosedDayRequestDto> closedDayList = new ArrayList<>();

        public Store toEntity() {
            return Store.builder()
                    .name(name)
                    .description(description)
                    .callNumber(callNumber)
                    .startTime(startTime)
                    .endTime(endTime)
                    .openingHours(openingHours)
                    .address(address)
                    .shortAddress(shortAddress)
                    .categoryIdx(categoryIdx)
                    .userIdx(userIdx)
                    .allowed(AllowedStatus.WAITING)
                    .build();
        }
    }

    // 휴무일 request
    @Getter
    public static class ClosedDayRequestDto {
        private String day;

        public StoreClosedDay toEntity(Store store) {
            return StoreClosedDay.builder()
                    .day(day)
                    .store(store)
                    .build();
        }
    }

    // 휴무일 response
    @Getter
    @NoArgsConstructor
    @AllArgsConstructor
    @Builder
    public static class ClosedDayResponseDto {
        private Long idx;
        private String day;

        public static ClosedDayResponseDto from(StoreClosedDay storeClosedDay) {
            return ClosedDayResponseDto.builder()
                    .idx(storeClosedDay.getIdx())
                    .day(storeClosedDay.getDay())
                    .build();
        }
    }

    @Getter
    @NoArgsConstructor
    @AllArgsConstructor
    @Builder
    public static class StoreResponseDto {
        private Long idx;
        private String name;
        private String description;
        private String callNumber;
        private LocalTime startTime;
        private LocalTime endTime;
        private String openingHours;
        private String address;
        private String shortAddress;
        private Long categoryIdx;
        private Long userIdx;
        private AllowedStatus allowed;
        List<ClosedDayResponseDto> closedDayList = new ArrayList<>();

        public static StoreResponseDto from(Store store, List<ClosedDayResponseDto> closedDayList) {
            return StoreResponseDto.builder()
                    .idx(store.getIdx())
                    .name(store.getName())
                    .description(store.getDescription())
                    .callNumber(store.getCallNumber())
                    .startTime(store.getStartTime())
                    .endTime(store.getEndTime())
                    .openingHours(store.getOpeningHours())
                    .address(store.getAddress())
                    .shortAddress(store.getShortAddress())
                    .categoryIdx(store.getCategoryIdx())
                    .userIdx(store.getUserIdx())
                    .allowed(store.getAllowed())
                    .closedDayList(closedDayList)
                    .build();
        }
    }
}
