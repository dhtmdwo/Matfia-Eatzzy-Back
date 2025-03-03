package com.example.appapi.orderProducts;

import com.example.appapi.orders.model.Orders;
import com.example.appapi.product.model.Products;
import lombok.Builder;
import lombok.Getter;

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
        public OrderProducts toEntity(Orders order, Products product) {
            return OrderProducts.builder()
                    .quantity(quantity)
                    .orders(order)
                    .products(product)
                    .build();
        }
    }
}
