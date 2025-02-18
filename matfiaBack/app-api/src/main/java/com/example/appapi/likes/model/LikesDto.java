package com.example.appapi.likes.model;

import com.example.appapi.likes.model.Likes;
import com.example.appapi.likes.model.LikesDto;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;


public class LikesDto {
    @Getter
    public static class CourseRegister {
        private String message;
        public Likes toEntity() {
            return Likes.builder()
                    .message(message)
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
                    .message(likes.getMessage())
                    .build();
        }

    }
    
}
