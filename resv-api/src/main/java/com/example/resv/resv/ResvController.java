package com.example.resv.resv;

import com.example.appapi.users.model.Users;
import com.example.common.BaseResponse;
import com.example.common.BaseResponseStatus;
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

    
    @Operation(summary = "식당 예약하기")
    @PostMapping("/create")
    public ResponseEntity<BaseResponse<ResvDto.ResvResponse>> create(@AuthenticationPrincipal Users user, @RequestBody ResvDto.CreateResvRequest dto) {
        ResvDto.ResvResponse resv = resvService.create(dto, user);

        return ResponseEntity.ok(new BaseResponse(BaseResponseStatus.SUCCESS, resv));
    }

    @Operation(summary = "예약한 식당 내역 보기 (클라이언트)")
    @GetMapping("/mypage/store")
    public ResponseEntity<BaseResponse<List<ResvDto.StoreRezResponse>>> storeList(@AuthenticationPrincipal Users user) {
        List<ResvDto.StoreRezResponse> responseList = resvService.storeList(user);
        return ResponseEntity.ok(new BaseResponse(BaseResponseStatus.SUCCESS, responseList));
    } // 마이페이지 클라이언트 예약한 식당 내역 보기

    @Operation(summary = "예약 취소하기(클라이언트)")
    @GetMapping("/mypage/deletestore")
    public ResponseEntity<BaseResponse<String>> deleteReservation(@RequestParam("idx") Long idx) {
        resvService.deleteReservation(idx);
        return ResponseEntity.ok(new BaseResponse(BaseResponseStatus.SUCCESS, "예약이 성공적으로 취소되었습니다."));
    } // 마이페이지 클라이언트 예약 취소

}
