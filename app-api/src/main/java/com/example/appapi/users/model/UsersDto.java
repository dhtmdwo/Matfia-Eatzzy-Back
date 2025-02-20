package com.example.appapi.users.model;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;

public class UsersDto {
    @AllArgsConstructor
    @RequiredArgsConstructor
    @Getter
    public static class SignupRequest {

        @Size(min=4, max=12, message="아이디는 4~12글자까지 입력 가능합니다.")
        @Pattern(regexp = "^[a-zA-Z0-9]+$", message="아이디는 영어, 숫자만 가능합니다. (대소문자 구분)")
        @NotBlank(message="아이디는 필수 입력값 입니다.")
        private String userId;

        @Size(min=8, message="비밀번호는 8자 이상 입력 해주십시오.")
        @Pattern(
                regexp = "^(?=.*[a-zA-Z])(?=.*\\d)(?=.*[@$!%*?&])[A-Za-z\\d@$!%*?&]{8,}$",
                message = "비밀번호는 영어, 숫자, 특수문자를 모두 포함하여 8자 이상으로 입력 해주십시오."
        )
        @NotBlank(message="비밀번호는 필수 입력값 입니다.")
        private String password;

        @Size(min=8, max=8, message="생년월일은 8자로 입력 해주십시오.")
        @NotBlank(message="생년월일은 필수 입력값 입니다.")
        private String birthDate;

        @Size(min=2, max=17, message="이름은 2~17글자까지 입력 가능합니다.")
        @Pattern(regexp = "^[가-핳]+$", message="이름은 한글만 입력 가능합니다.")
        private String name;

        @Size(max=254, message="이메일은 254자까지 입력 가능합니다.")
        @Email
        @NotBlank(message="이메일은 필수 입력값 입니다.")
        private String email;

        @NotBlank(message="주소는 필수 입력값 입니다.")
        private String address;

        @NotBlank(message="상세주소는 필수 입력값 입니다.")
        private String addressDetail;

        @Size(max=13, message="전화번호는 13자까지 입력 가능합니다.")
        @Pattern(regexp = "^[0-9]+$", message="전화번호는 숫자만 입력 해주십시오.")
        @NotBlank(message="전화번호는 필수 입력값 입니다.")
        private String phone;

        @NotBlank(message="회원타입은 필수 입력값 입니다.")
        private UserType userType;

        public Users toEntity(String encodedPassword) {
            Users users = Users.builder()
                    .userId(userId)
                    .password(encodedPassword)
                    .birthDate(birthDate)
                    .name(name)
                    .email(email)
                    .address(address)
                    .addressDetail(addressDetail)
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
        private UserType userType;

        public static SignupResponse from(Users entity) {
            return new SignupResponse(
                    entity.getIdx(), entity.getUserId(), entity.getUserType()
            );
        }
    }
}
