package com.example.appapi.orders;

import com.example.appapi.orders.model.OrdersDto;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/app/orders")
public class OrdersController {
    private final OrdersService ordersService;
    @PostMapping("/register")
    public String register(@RequestBody OrdersDto.RegisterRequest dto) {
        ordersService.register(dto);
        return "주문 등록 완료";
    }
    @GetMapping("/list")
    public ResponseEntity<List<OrdersDto.ListResponse>> list() {
        List<OrdersDto.ListResponse> resp = ordersService.getList();
        return ResponseEntity.ok(resp);
    }
    @GetMapping("{idx}")
    public ResponseEntity<OrdersDto.ReadResponse> getRead(@PathVariable Long idx) {
        OrdersDto.ReadResponse resp = ordersService.getRead(idx);
        return ResponseEntity.ok(resp);
    }
}
