package com.example.appapi.orderProducts;

import com.example.appapi.orderProducts.model.OrderProductsDto;
import com.example.appapi.users.model.Users;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/app/orderproducts")
public class OrderProductsController {
    private final OrderProductsService orderProductsService;
    @PostMapping("/orderregister")      // 장바구니 -> 주문서 작성 페이지 이동 시 장바구니에 있던 상품들 DB 등록
    public void register
            (@RequestBody OrderProductsDto.OrderRegisterRequest dto,
             @AuthenticationPrincipal Users user) {
        orderProductsService.register(dto, user);
    }
    @GetMapping("/list/{ordersIdx}")    // 주문서 작성 페이지에서 주문할 상품 목록
    public ResponseEntity<List<OrderProductsDto.ListProductsResponse>> list(@PathVariable Long ordersIdx) {
        List<OrderProductsDto.ListProductsResponse> resp = orderProductsService.list(ordersIdx);
        return ResponseEntity.ok(resp);
    }
}
