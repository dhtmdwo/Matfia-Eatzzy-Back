package com.example.appapi.payment.model;

import com.example.appapi.like.model.Likes;
import lombok.Getter;

public class PaymentDto {

    @Getter
    public static class PaymentRegister {
        private String status;
        int price;
        public Payment toEntity() {
            return Payment.builder()
                    .price(price)
                    .status(status)
                    .build();
        }
    }
}
