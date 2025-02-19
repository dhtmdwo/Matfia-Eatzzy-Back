package com.example.appapi.users.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;

public class UsersDto {
    @AllArgsConstructor
    @RequiredArgsConstructor
    @Getter
    public static class SignupRequest {

        private String userId;
        private String password;
        private String birthDate;
        private String email;
        private String postalCode;
        private String address;
        private String phone;
        private String userType;

        public Users toEntity(String encodedPassword) {
            Users users = Users.builder()
                    .userId(userId)
                    .password(encodedPassword)
                    .birthDate(birthDate)
                    .email(email)
                    .postalCode(postalCode)
                    .address(address)
                    .phone(phone)
                    .userType(userType)
                    .build();
            return users;
        }
    }
    @AllArgsConstructor
    @NoArgsConstructor
    @Getter
    public static class SignupResponse {
        private Long idx;
        private String userId;
        private String userType;

        public static SignupResponse from(Users entity) {
            return new SignupResponse(
                    entity.getIdx(), entity.getUserId(), entity.getUserType()
            );
        }
    }
}
