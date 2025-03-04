package com.example.appapi.orderProducts;

import com.example.appapi.orderProducts.model.OrderProductsDto;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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
    @GetMapping("/list/{ordersIdx}")
    public ResponseEntity<List<OrderProductsDto.ListProductsResponse>> list(@PathVariable Long ordersIdx) {
        List<OrderProductsDto.ListProductsResponse> resp = orderProductsService.list(ordersIdx);
        return ResponseEntity.ok(resp);
    }
}
