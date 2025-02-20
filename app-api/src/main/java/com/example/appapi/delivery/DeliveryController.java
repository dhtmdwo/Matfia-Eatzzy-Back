package com.example.appapi.delivery;

import com.example.appapi.delivery.model.Delivery;
import com.example.appapi.delivery.model.DeliveryDto;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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

    @GetMapping("/list")
    public ResponseEntity<List<DeliveryDto.ListResponse>> list() {
        List<DeliveryDto.ListResponse> resp = deliveryService.getList();
        return ResponseEntity.ok(resp);
    }
}
