package com.example.appapi.orders;

import com.example.appapi.orders.model.Orders;
import com.example.appapi.orders.model.OrdersDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class OrdersService {
    private final OrdersRepository ordersRepository;
    public void register(OrdersDto.RegisterRequest dto) {
        ordersRepository.save(dto.toEntity());
    }

    public List<OrdersDto.ListResponse> getList() {
        List<Orders> dto = ordersRepository.findAll();
        return dto.stream().map(OrdersDto.ListResponse::from).collect(Collectors.toList());
    }
}
