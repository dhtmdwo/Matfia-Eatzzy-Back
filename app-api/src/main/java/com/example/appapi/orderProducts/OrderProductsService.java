package com.example.appapi.orderProducts;

import com.example.appapi.orderProducts.model.OrderProducts;
import com.example.appapi.orderProducts.model.OrderProductsDto;
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
        int dtototalPrice = 0;
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
            dtototalPrice += orderProductDto.getPrice() * quantity;

            OrderProducts orderProduct = orderProductDto.toEntity(order, product);
            orderProductsRepository.save(orderProduct);
        }
        if (totalPrice != dtototalPrice) {
            throw new IllegalStateException("총 상품 가격 불일치: 계산된 가격 = " + totalPrice + ", 입력된 가격 = " + dtototalPrice);
        }

        ordersRepository.updateOrderPrice(savedOrder.getIdx(), totalPrice);
    }

    public List<OrderProductsDto.ListProductsResponse> list(Long ordersIdx) {
        List<OrderProducts> result = orderProductsRepository.findAllByOrdersIdx(ordersIdx);
        List<OrderProductsDto.ListProductsResponse> dtoList = new ArrayList<>();
        for (OrderProducts orderProducts : result) {
            Products product = orderProducts.getProducts();
            OrderProductsDto.ListProductResponse dto = OrderProductsDto.ListProductResponse.from(product);
            OrderProductsDto.ListProductsResponse resp = OrderProductsDto.ListProductsResponse.from(orderProducts, dto);
            dtoList.add(resp);
        }
        return dtoList;
    }
}
