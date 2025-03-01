package com.example.appapi.orders.model;

import com.example.appapi.orderProducts.OrderProductsDto;
import lombok.Builder;
import lombok.Getter;

import java.util.List;

public class OrdersDto {
    @Builder
    @Getter
    public static class OrdersRegister {
        private String message;
    }
    @Builder
    @Getter
    public static class RegisterRequest {
        private String message;
        private String status;

        public Orders toEntity(int totalPrice) {
            return Orders.builder()
                    .price(totalPrice)
                    .message(message)
                    .status("Pending")
                    .build();
        }
    }


    @Getter
    @Builder
    public static class ListResponse {
        private Long idx;
        private int price;
        private String message;
        private String status;

        public static ListResponse from(Orders orders) {
            return ListResponse.builder()
                    .idx(orders.getIdx())
                    .price(orders.getPrice())
                    .message(orders.getMessage())
                    .status(orders.getStatus())
                    .build();
        }
    }
    @Getter
    @Builder
    public static class ReadResponse {
        private Long idx;
        private int price;
        private String message;
        private String status;

        public static ReadResponse from(Orders orders) {
            return ReadResponse.builder()
                    .idx(orders.getIdx())
                    .price(orders.getPrice())
                    .message(orders.getMessage())
                    .status(orders.getStatus())
                    .build();
        }
    }

    @Getter
    @Builder
    public static class OrdersResponse {
        private Long idx;
        private int price;
        private String message;
        private String status;
        private List<OrderProductsDto.OrderProductResponse> OrderProductResponse;

        public static OrdersResponse from(Orders orders, List<OrderProductsDto.OrderProductResponse> resp) {
            return OrdersResponse.builder()
                    .idx(orders.getIdx())
                    .price(orders.getPrice())
                    .message(orders.getMessage())
                    .status(orders.getStatus())
                    .OrderProductResponse(resp)
                    .build();
        }
    }
}
