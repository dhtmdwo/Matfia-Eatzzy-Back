package com.example.appapi.orders;

import com.example.appapi.orders.model.OrdersDto;
import com.example.appapi.users.model.Users;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RestController
@Tag(name = "주문 기능", description = "주문서 관련 기능")
@RequestMapping("/app/orders")
public class OrdersController {
    private final OrdersService ordersService;
    @Operation(summary = "상품 주문하기", description = "장바구니에서 상품 주문하기 클릭")
    @PostMapping("/register/{idx}")     // 주문 둥록하기
    public String register(@PathVariable Long idx, @RequestBody OrdersDto.OrdersRegister dto){
        ordersService.register(idx, dto);
        return "주문 등록 완료";
    }
//    @GetMapping("/list")
//    public ResponseEntity<List<OrdersDto.ListResponse>> list() {
//        List<OrdersDto.ListResponse> resp = ordersService.getList();
//        return ResponseEntity.ok(resp);
//    }
//    @GetMapping("{idx}")
//    public ResponseEntity<OrdersDto.ReadResponse> getRead(@PathVariable Long idx) {
//        OrdersDto.ReadResponse resp = ordersService.getRead(idx);
//        return ResponseEntity.ok(resp);
//    }
    @Operation(summary = "내 주문 보기", description = "내 주문 목록 보기")
    @GetMapping("/orderlist")   // 내 주문 보기
    public ResponseEntity<List<OrdersDto.OrdersResponse>> orderList(@AuthenticationPrincipal Users user) {
        List<OrdersDto.OrdersResponse> resp = ordersService.getOrderList(user.getIdx());
        return ResponseEntity.ok(resp);
    }

    @GetMapping("/mypage/orderlist")
    public ResponseEntity<List<OrdersDto.OrderMypageList>> storeList(@RequestParam("idx") Long idx) {
        List<OrdersDto.OrderMypageList> responseList = ordersService.orderList(idx);
        return ResponseEntity.ok(responseList);
    } // 마이페이지 클라이언트 주문 리스트 보기

    @GetMapping("/mypage/orderdetails")
    public ResponseEntity<OrdersDto.OrderMypageDetails> storeDetails(@RequestParam("idx") Long idx, @RequestParam("orderIdx") Long orderIdx) {
        OrdersDto.OrderMypageDetails response = ordersService.orderDetails(idx, orderIdx);
        return ResponseEntity.ok(response);
    } // 마이페이지 클라이언트 주문 상세 보기
    


    @GetMapping("/{orderIdx}")  // 주문 상세보기
    public ResponseEntity<OrdersDto.OrdersResponse> orderRead(@PathVariable Long orderIdx){
        OrdersDto.OrdersResponse resp = ordersService.getOrderRead(orderIdx);
        return ResponseEntity.ok(resp);
    }

}
