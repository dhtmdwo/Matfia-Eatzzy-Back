package com.example.appapi.likes;

import com.example.appapi.likes.model.LikesDto;
import com.example.appapi.reservation.model.ReservationDto;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/likes")
public class LikesController {
    private final LikesService likesService;
    @PostMapping("/register")
    public String register(@RequestBody LikesDto.LikeRegister dto) {
        likesService.register(dto);
        return "좋아요 등록 완료";
    }
    @GetMapping("/list")
    public ResponseEntity<List<LikesDto.LikesResponse>> list() {
        List<LikesDto.LikesResponse> response = likesService.list();

        return ResponseEntity.ok(response);
    }

    @GetMapping("/{likesIdx}")
    public ResponseEntity<LikesDto.LikesResponse> read(@PathVariable Long likesIdx) {
        LikesDto.LikesResponse response = likesService.read(likesIdx);

        return ResponseEntity.ok(response);
    }

    @GetMapping("/mypage/store")
    public ResponseEntity<List<LikesDto.StoreLikesResponse>> storeList(@RequestParam("idx") Long idx) {
        List<LikesDto.StoreLikesResponse> responseList = likesService.storeList(idx);
        return ResponseEntity.ok(responseList);
    } // 마이페이지 클라이언트 식당 내역 보기

    @GetMapping("/mypage/deletestore")
    public ResponseEntity<String> deleteLikes(@RequestParam("idx") Long idx) {
        likesService.deleteLikes(idx);
        return ResponseEntity.ok("삭제 완료");
    } // 마이페이지 클라이언트 식당 좋아요 삭제


}
