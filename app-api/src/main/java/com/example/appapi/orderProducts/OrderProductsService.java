package com.example.appapi.orderProducts;

import com.example.appapi.orders.OrdersRepository;
import com.example.appapi.orders.model.Orders;
import com.example.appapi.product.model.Products;
import com.example.appapi.product.repository.ProductsRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@RequiredArgsConstructor
@Service
public class OrderProductsService {
    private final OrdersRepository ordersRepository;
    private final OrderProductsRepository orderProductsRepository;
    private final ProductsRepository productsRepository;
    @Transactional
    public void register(List<OrderProductsDto.OrderProductRegisterRequest> dtoList) {
        int totalPrice = 0;
        Orders order = Orders.builder()
                .status("Pending")
                .message("")
                .build();
        Orders savedOrder = ordersRepository.save(order);

        for (OrderProductsDto.OrderProductRegisterRequest orderProductDto : dtoList) {
            Products product = productsRepository.findByIdx(orderProductDto.getProductIdx());
            if (product == null) {
                throw new IllegalArgumentException("존재하지 않는 상품 : " + orderProductDto.getProductIdx());
            }
            int quantity = orderProductDto.getQuantity();
            int productPrice = product.getPrice() * quantity;
            totalPrice += productPrice;

            OrderProducts orderProduct = orderProductDto.toEntity(order, product);
            orderProductsRepository.save(orderProduct);
        }
        ordersRepository.updateOrderPrice(savedOrder.getIdx(), totalPrice);
    }
}
