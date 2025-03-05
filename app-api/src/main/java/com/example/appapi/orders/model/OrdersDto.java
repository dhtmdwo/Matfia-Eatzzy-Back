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

    @Getter
    @Builder
    public static class OrderMypageList {
        private Long idx;
        private String orderDate;
        private String status;
        private List<MyOrder> myOrderList;

        public static OrderMypageList from(Orders orders, List<MyOrder> myOrderList) {
            return OrderMypageList.builder()
                    .idx(orders.getIdx()) // 주문 번호
                    .orderDate(orders.getOrderDate()) // 주문 날짜
                    .status(orders.getStatus()) // 주문 상태
                    .myOrderList(myOrderList)
                    .build();
        }
    } // 마이페이지 클라이언트 주문 목록

    @Getter
    @Builder
    public static class MyOrder {
        private String name;
        private int quantity;

        public static MyOrder from(String productName, int quantity) {
            return MyOrder.builder()
                    .name(productName) // 주문 상태
                    .quantity(quantity)
                    .build();
        }
    } // 마이페이지 클라이언트 주문 리스트 => id, 상품 이름, 상품 개수


}
