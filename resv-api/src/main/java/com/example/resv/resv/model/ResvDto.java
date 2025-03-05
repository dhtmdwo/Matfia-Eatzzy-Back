package com.example.resv.resv.model;

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
}