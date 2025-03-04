package com.example.appapi.orderProducts;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/app/orderproducts")
public class OrderProductsController {
    private final OrderProductsService orderProductsService;
    @PostMapping("/orderregister")
    public void register(@RequestBody List<OrderProductsDto.OrderProductRegisterRequest> dto) {
        orderProductsService.register(dto);
    }
}
