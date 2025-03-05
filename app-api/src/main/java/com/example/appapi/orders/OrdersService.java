package com.example.appapi.orders;

import com.example.appapi.orderProducts.model.OrderProducts;
import com.example.appapi.orderProducts.model.OrderProductsDto;
import com.example.appapi.orders.model.Orders;
import com.example.appapi.orders.model.OrdersDto;
import com.example.appapi.product.model.Products;
import com.example.appapi.product.repository.ProductsRepository;
import com.example.appapi.reservation.model.Reservation;
import com.example.appapi.reservation.model.ReservationDto;
import com.example.appapi.store.model.Store;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class OrdersService {
    private final OrdersRepository ordersRepository;
    @Transactional
    public void register(Long idx, OrdersDto.OrdersRegister dto) {
        ordersRepository.updateOrderMessageAndStatus(idx, dto.getMessage());
    }

//    public List<OrdersDto.ListResponse> getList() {
//        List<Orders> dto = ordersRepository.findAll();
//        return dto.stream().map(OrdersDto.ListResponse::from).collect(Collectors.toList());
//    }
//
//    public OrdersDto.ReadResponse getRead(Long idx) {
//        Orders dto = ordersRepository.findByIdx(idx);
//        return OrdersDto.ReadResponse.from(dto);
//    }

    public List<OrdersDto.OrdersResponse> getOrderList(Long userIdx) {
        List<Orders> ordersList = ordersRepository.findAllWithOrderProductsAndProducts(userIdx);
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

//    public List<OrdersDto.OrderMypageList> orderList(Long idx) {
//        List<Orders> orders = ordersRepository.findMyAllOrders(idx);
//        List<OrdersDto.OrderMypageList> responseList = new ArrayList<>();
//
//        for (Orders order : orders) {
//            List<OrderProducts> orderProductList = order.getOrderProducts();
//            List<OrdersDto.MyOrder> myOrderList = new ArrayList<>();
//            for(OrderProducts orderProduct : orderProductList) {
//                Products products = orderProduct.getProducts();
//                String productName = products.getName(); // 상품
//                int quantity = orderProduct.getQuantity(); // 상품 수량
//                myOrderList.add(OrdersDto.MyOrder.from(productName, quantity));
//            }
//            responseList.add(OrdersDto.OrderMypageList.from(order, myOrderList));
//        }
//
//        return responseList;
//    } // 마이페이지 클라이언트 주문 리스트 보기


    public OrdersDto.OrdersResponse getOrderRead(Long orderIdx) {
        Optional<Orders> order = ordersRepository.findByIdWithOrderProductsAndProducts(orderIdx);

        if (order.isEmpty()) {
            return null;
        }
        List<OrderProductsDto.OrderProductResponse> orderProductResponses = new ArrayList<>();
        for (OrderProducts orderProduct : order.get().getOrderProducts()) {
            Products products = orderProduct.getProducts();
            OrderProductsDto.ProductsResponse productResponse = OrderProductsDto.ProductsResponse.of(products);
            orderProductResponses.add(OrderProductsDto.OrderProductResponse.of(orderProduct, productResponse));
        }
        return OrdersDto.OrdersResponse.from(order.get(), orderProductResponses);
    }

}
