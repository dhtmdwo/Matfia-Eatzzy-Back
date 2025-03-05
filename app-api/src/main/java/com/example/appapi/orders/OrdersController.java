package com.example.appapi.orders;

import com.example.appapi.orders.model.OrdersDto;
import com.example.appapi.reservation.model.ReservationDto;
import com.example.appapi.users.model.Users;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/app/orders")
public class OrdersController {
    private final OrdersService ordersService;
    @PostMapping("/register/{idx}")
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
    @GetMapping("/orderlist")
    public ResponseEntity<List<OrdersDto.OrdersResponse>> orderList(@AuthenticationPrincipal Users user) {
        List<OrdersDto.OrdersResponse> resp = ordersService.getOrderList(user.getIdx());
        return ResponseEntity.ok(resp);
    }


//    @GetMapping("/mypage/orderlist")
//    public ResponseEntity<List<OrdersDto.OrderMypageList>> storeList(@RequestParam("idx") Long idx) {
//        List<OrdersDto.OrderMypageList> responseList = ordersService.orderList(idx);
//        return ResponseEntity.ok(responseList);
//    } // 마이페이지 클라이언트 주문 리스트 보기


    @GetMapping("/{orderIdx}")
    public ResponseEntity<OrdersDto.OrdersResponse> orderRead(@PathVariable Long orderIdx){
        OrdersDto.OrdersResponse resp = ordersService.getOrderRead(orderIdx);
        return ResponseEntity.ok(resp);
    }

}
