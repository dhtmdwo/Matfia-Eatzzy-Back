package com.example.appapi.store.model;

import com.example.appapi.category.model.Category;
import com.example.appapi.users.model.Users;
import jakarta.validation.constraints.NotBlank;
import lombok.*;
import org.springframework.data.domain.Page;

import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class StoreDto {

    // store 생성 request
    @Getter
    public static class CreateStoreRequestDto {
        private String name;
        private String description;
        private String callNumber;
        private LocalTime startTime;
        private LocalTime endTime;
        private String openingHours;
        private String address;
        private String shortAddress;
        private Long categoryIdx;

        List<ClosedDayRequestDto> closedDayList = new ArrayList<>();
        private List<String> imagePaths;

        public Store toEntity(Users user, Category category) {
            return Store.builder()
                    .name(name)
                    .description(description)
                    .callNumber(callNumber)
                    .startTime(startTime)
                    .endTime(endTime)
                    .openingHours(openingHours)
                    .address(address)
                    .shortAddress(shortAddress)
                    .user(user)
                    .allowed(AllowedStatus.WAITING)
                    .category(category)
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
        private AllowedStatus allowed;
        private String categoryName;
        @Setter
        List<ClosedDayResponseDto> closedDayList = new ArrayList<>();
        @Setter
        private List<String> imagePaths;


        public static StoreResponseDto from(Store store) {
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
                    .allowed(store.getAllowed())
                    .categoryName(store.getCategory().getName())
                    .closedDayList(store.getClosedDayList().stream().map(StoreDto.ClosedDayResponseDto::from).collect(Collectors.toList()))
                    .imagePaths(
                            store.getImages() == null ? List.of() : store.getImages().stream()
                                    .map(image -> image.getImagePath()).toList()
                    )
                    .build();
        }
    }

    @Getter
    @NoArgsConstructor
    @AllArgsConstructor
    @Builder
    public static class StoreSimpleResponseDto {
        private Long idx;
        private String name;
        private String shortAddress;
        private String categoryName;
        private String thumbnail;

        public static StoreSimpleResponseDto from(Store store) {
            return StoreSimpleResponseDto.builder()
                    .idx(store.getIdx())
                    .name(store.getName())
                    .shortAddress(store.getShortAddress())
                    .categoryName(store.getCategory().getName())
                    .thumbnail(store.getImages().get(0).getImagePath())
                    .build();
        }
    }

    @Getter
    @NoArgsConstructor
    @AllArgsConstructor
    @Builder
    public static class StorePageResponseDto {
        private int page;
        private int size;
        private long totalElements;
        private int totalPages;
        private boolean hasNext;
        private boolean hasPrevious;

        private List<StoreSimpleResponseDto> stores;

        public static StorePageResponseDto from(Page<Store> storePage) {
            return StorePageResponseDto.builder()
                    .page(storePage.getNumber())
                    .size(storePage.getSize())
                    .totalElements(storePage.getTotalElements())
                    .totalPages(storePage.getTotalPages())
                    .hasNext(storePage.hasNext())
                    .hasPrevious(storePage.hasPrevious())
                    .stores(storePage.stream().map(StoreDto.StoreSimpleResponseDto::from).collect(Collectors.toList()))
                    .build();
        }
    }


    @Getter
    public static class UpdateAllowedRequestDto {
        private Long idx;
        private AllowedStatus allowed;

        public Store toEntity() {
            return Store.builder()
                    .idx(idx)
                    .allowed(allowed)
                    .build();
        }
    }
}
