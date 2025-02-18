package com.example.appapi.like.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;


public class LikesDto {
    @Getter
    public static class CourseRegister {
        public Likes toEntity() {
            return Likes.builder()
                    .build();
        }
    }
}
