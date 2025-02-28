package com.example.appapi.payment.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.stream.Collectors;

public class PaymentDto {
    @Getter
    public static class PaymentRegister {
        private String status;
        private int price;
        public Payment toEntity() {
            return Payment.builder()
                    .price(price)
                    .status(status)
                    .build();
        }
    }
    @Getter
    @NoArgsConstructor
    @AllArgsConstructor
    @Builder
    public static class PaymentResponse {
        private String status;
        private int price;

        public static PaymentResponse from(Payment payment) {
            return PaymentResponse.builder()
                    .status(payment.getStatus())
                    .price(payment.getPrice())
                    .build();
        }

    }

}
