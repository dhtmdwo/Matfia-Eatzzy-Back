package com.example.appapi.orderProducts.model;

import com.example.appapi.orders.model.Orders;
import com.example.appapi.product.model.Products;
import lombok.Builder;
import lombok.Getter;

import java.util.List;

public class OrderProductsDto {
    @Builder
    @Getter
    public static class OrderProductResponse {
        private Long idx;
        private int quantity;
        private ProductsResponse productsResponse;

        public static OrderProductResponse of(OrderProducts orderProducts, OrderProductsDto.ProductsResponse products) {
            return OrderProductResponse.builder()
                    .idx(orderProducts.getIdx())
                    .quantity(orderProducts.getQuantity())
                    .productsResponse(products)
                    .build();
        }
    }
    @Builder
    @Getter
    public static class ProductsResponse {
        private String name;
        private int price;
        public static ProductsResponse of(Products products) {
            return ProductsResponse.builder()
                    .name(products.getName())
                    .price(products.getPrice())
                    .build();
        }
    }

    @Builder
    @Getter
    public static class OrderProductRegisterRequest {
        private int quantity;
        private Long productIdx;
        private int price;
        public OrderProducts toEntity(Orders order, Products product) {
            return OrderProducts.builder()
                    .quantity(quantity)
                    .orders(order)
                    .products(product)
                    .build();
        }
    }

    @Builder
    @Getter
    public static class ListProductsResponse {
        private Long idx;
        private int quantity;
        private OrderProductsDto.ListProductResponse ListProductResponse;
        public static ListProductsResponse from (OrderProducts orderProducts, OrderProductsDto.ListProductResponse products) {
            return ListProductsResponse.builder()
                    .idx(orderProducts.getIdx())
                    .quantity(orderProducts.getQuantity())
                    .ListProductResponse(products)
                    .build();
        }
    }
    @Builder
    @Getter
    public static class ListProductResponse{
        private Long idx;
        private String name;
        private int price;
        public static ListProductResponse from(Products product){
            return ListProductResponse.builder()
                    .idx(product.getIdx())
                    .name(product.getName())
                    .price(product.getPrice())
                    .build();

        }
    }
}
