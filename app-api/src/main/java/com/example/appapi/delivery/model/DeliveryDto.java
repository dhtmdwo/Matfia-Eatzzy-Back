package com.example.appapi.delivery.model;

import lombok.Builder;
import lombok.Getter;

public class DeliveryDto {
    @Builder
    @Getter
    public static class RegisterRequest {
        private String delivery_status;
        private String courier_company;
        private int tracking_number;

        public Delivery toEntity() {
            return Delivery.builder()
                    .delivery_status(delivery_status)
                    .courier_company(courier_company)
                    .tracking_number(tracking_number)
                    .build();
        }
    }
}
