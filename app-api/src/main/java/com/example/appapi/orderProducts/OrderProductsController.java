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
    @PostMapping("/orderregister")      // 장바구니 -> 주문서 작성 페이지 이동 시 장바구니에 있던 상품들 DB 등록
    public void register(@RequestBody List<OrderProductsDto.OrderProductRegisterRequest> dto) {
        orderProductsService.register(dto);
    }
    @GetMapping("/list/{ordersIdx}")    // 주문서 작성 페이지에서 주문할 상품 목록
    public ResponseEntity<List<OrderProductsDto.ListProductsResponse>> list(@PathVariable Long ordersIdx) {
        List<OrderProductsDto.ListProductsResponse> resp = orderProductsService.list(ordersIdx);
        return ResponseEntity.ok(resp);
    }
}
