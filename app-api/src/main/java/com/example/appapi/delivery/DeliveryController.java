package com.example.appapi.delivery;

import com.example.appapi.delivery.model.Delivery;
import com.example.appapi.delivery.model.DeliveryDto;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
@RequestMapping("/app/delivery")
public class DeliveryController {
    private final DeliveryService deliveryService;
    @PostMapping("/register")
    public String register(@RequestBody DeliveryDto.RegisterRequest dto) {
        deliveryService.register(dto);
        return "배송 등록 완료";
    }
}
