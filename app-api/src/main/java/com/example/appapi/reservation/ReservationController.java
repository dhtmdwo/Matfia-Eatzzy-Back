package com.example.appapi.reservation;

import com.example.appapi.reservation.model.ReservationDto;
import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/reservation")
public class ReservationController {
    private final ReservationService reservationService;

    @Operation(summary = "예약한 식당 내역 보기(클라이언트)")
    @GetMapping("/mypage/store")
    public ResponseEntity<List<ReservationDto.StoreRezResponse>> storeList(@RequestParam("idx") Long idx) {
        List<ReservationDto.StoreRezResponse> responseList = reservationService.storeList(idx);
        return ResponseEntity.ok(responseList);
    } // 마이페이지 클라이언트 예약한 식당 내역 보기

    @Operation(summary = "예약 취소하기(클라이언트)")
    @GetMapping("/mypage/deletestore")
    public ResponseEntity<String> deleteReservation(@RequestParam("idx") Long idx) {
        reservationService.deleteReservation(idx);
        return ResponseEntity.ok("삭제 완료");
    } // 마이페이지 클라이언트 예약 취소

}
