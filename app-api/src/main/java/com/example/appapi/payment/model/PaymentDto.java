package com.example.appapi.payment.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.stream.Collectors;

public class PaymentDto {
    @Getter
    public static class PaymentRegister {
        private String paymentId;
        private String txId;
        public Payment toEntity() {
            return Payment.builder()
                    .paymentId(paymentId)
                    .txId(txId)
                    .build();
        }
    }
    @Getter
    @NoArgsConstructor
    @AllArgsConstructor
    @Builder
    public static class PaymentResponse {
        private String status;
        private String paymentId;

        public static PaymentResponse from(Payment payment) {
            return PaymentResponse.builder()
                    .paymentId(payment.getPaymentId())
                    .status(payment.getStatus())
                    .build();
        }

    }

}
