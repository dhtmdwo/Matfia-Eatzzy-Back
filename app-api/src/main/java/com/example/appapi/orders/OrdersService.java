package com.example.appapi.orders;

import com.example.appapi.orderProducts.model.OrderProducts;
import com.example.appapi.orderProducts.model.OrderProductsDto;
import com.example.appapi.orders.model.Orders;
import com.example.appapi.orders.model.OrdersDto;
import com.example.appapi.product.model.Products;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class OrdersService {
    private final OrdersRepository ordersRepository;
    @Transactional
    public void register(Long idx, OrdersDto.OrdersRegister dto) {
        ordersRepository.updateOrderMessageAndStatus(idx, dto.getMessage());
    }

    public List<OrdersDto.ListResponse> getList() {
        List<Orders> dto = ordersRepository.findAll();
        return dto.stream().map(OrdersDto.ListResponse::from).collect(Collectors.toList());
    }

    public OrdersDto.ReadResponse getRead(Long idx) {
        Orders dto = ordersRepository.findByIdx(idx);
        return OrdersDto.ReadResponse.from(dto);
    }

    public List<OrdersDto.OrdersResponse> getOrderList() {
        List<Orders> ordersList = ordersRepository.findAllWithOrderProductsAndProducts();
        List<OrdersDto.OrdersResponse> ordersResponseList = new ArrayList<>();

        for (Orders order : ordersList) {
            List<OrderProductsDto.OrderProductResponse> orderProductResponses = new ArrayList<>();

            for (OrderProducts orderProduct : order.getOrderProducts()) {
                Products products = orderProduct.getProducts();
                OrderProductsDto.ProductsResponse productResponse = OrderProductsDto.ProductsResponse.of(products);
                orderProductResponses.add(OrderProductsDto.OrderProductResponse.of(orderProduct, productResponse));
            }
            OrdersDto.OrdersResponse ordersResponse = OrdersDto.OrdersResponse.from(order, orderProductResponses);
            ordersResponseList.add(ordersResponse);
        }
        return ordersResponseList;
    }
}
