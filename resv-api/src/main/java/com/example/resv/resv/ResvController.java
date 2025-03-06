package com.example.resv.resv;

import com.example.appapi.users.model.Users;
import com.example.resv.resv.model.ResvDto;
import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/resv")
public class ResvController {
    private final ResvService resvService;

    @PostMapping("/create")
    public ResponseEntity<ResvDto.ResvResponse> create(@AuthenticationPrincipal Users user, @RequestBody ResvDto.CreateResvRequest dto) {
        ResvDto.ResvResponse resv = resvService.create(dto, user);

        return ResponseEntity.ok(resv);
    }

    @Operation(summary = "예약한 식당 내역 보기(클라이언트)")
    @GetMapping("/mypage/store")
    public ResponseEntity<List<ResvDto.StoreRezResponse>> storeList(@RequestParam("idx") Long idx) {
        List<ResvDto.StoreRezResponse> responseList = resvService.storeList(idx);
        return ResponseEntity.ok(responseList);
    } // 마이페이지 클라이언트 예약한 식당 내역 보기

    @Operation(summary = "예약 취소하기(클라이언트)")
    @GetMapping("/mypage/deletestore")
    public ResponseEntity<String> deleteReservation(@RequestParam("idx") Long idx) {
        resvService.deleteReservation(idx);
        return ResponseEntity.ok("삭제 완료");
    } // 마이페이지 클라이언트 예약 취소

}
