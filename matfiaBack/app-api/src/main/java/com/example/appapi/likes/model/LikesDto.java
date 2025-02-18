package com.example.appapi.likes.model;

import lombok.Getter;


public class LikesDto {
    @Getter
    public static class CourseRegister {
        public Likes toEntity() {
            return Likes.builder()
                    .build();
        }
    }
}
