package com.example.appapi.orders.model;

import lombok.Builder;
import lombok.Getter;

public class OrdersDto {
    @Builder
    @Getter
    public static class RegisterRequest {
        private int quantity;
        private int price;
        private String message;
        private String status;

        public Orders toEntity() {
            return Orders.builder()
                    .quantity(quantity)
                    .price(price)
                    .message(message)
                    .status("Pending")
                    .build();
        }
    }
    @Getter
    @Builder
    public static class ListResponse {
        private Long idx;
        private int quantity;
        private int price;
        private String message;
        private String status;

        public static ListResponse from(Orders orders) {
            return ListResponse.builder()
                    .idx(orders.getIdx())
                    .quantity(orders.getQuantity())
                    .price(orders.getPrice())
                    .message(orders.getMessage())
                    .status(orders.getStatus())
                    .build();
        }
    }
}
