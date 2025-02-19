package com.example.appapi.orders;

import com.example.appapi.orders.model.OrdersDto;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
@RequestMapping("/orders")
public class OrdersController {
    private final OrdersService ordersService;
    @PostMapping("/register")
    public String register(@RequestBody OrdersDto.RegisterRequest dto) {
        ordersService.register(dto);
        return "주문 등록 완료";
    }
}
