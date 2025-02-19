package com.example.appapi.orders;

import com.example.appapi.orders.model.Orders;
import com.example.appapi.orders.model.OrdersDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class OrdersService {
    private final OrdersRepository ordersRepository;
    public void register(OrdersDto.RegisterRequest dto) {
        ordersRepository.save(dto.toEntity());
    }
}
