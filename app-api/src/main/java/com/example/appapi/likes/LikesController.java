package com.example.appapi.likes;

import com.example.appapi.likes.model.LikesDto;
import com.example.appapi.users.model.Users;
import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/app/likes")
public class LikesController {
    private final LikesService likesService;

    @Operation(summary = "좋아요 한 식당 보기(클라이언트)")
    @GetMapping("/mypage/store")
    public ResponseEntity<List<LikesDto.StoreLikesResponse>> storeList(@AuthenticationPrincipal Users users) {
        Long idx = users.getIdx();
        List<LikesDto.StoreLikesResponse> responseList = likesService.storeList(idx);
        return ResponseEntity.ok(responseList);
    } // 마이페이지 클라이언트 좋아요 한 식당 내역 보기

    @Operation(summary = "식당 좋아요, 좋아요 취소(클라이언트)")
    @GetMapping("/mypage/deletestore/{storeIdx}")
    public ResponseEntity<String> deleteLikes(@AuthenticationPrincipal Users users, @PathVariable Long storeIdx) {
        Long userIdx = users.getIdx();
        likesService.deleteLikes(userIdx, storeIdx);
        return ResponseEntity.ok("작업 완료");
    } // 마이페이지 클라이언트 식당 좋아요 삭제
}
