package com.example.appapi.admin;

import com.example.appapi.store.StoreService;
import com.example.appapi.store.model.StoreDto;
import com.example.common.BaseResponse;
import com.example.common.BaseResponseStatus;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RestController
@RequestMapping("/app/admin")
public class AdminController {
    private final AdminService adminService;

    // 식당 목록 조회
    @GetMapping("/store/list")
    public ResponseEntity<BaseResponse<StoreDto.StorePageResponseDto>> getStoreList(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size) {
        StoreDto.StorePageResponseDto response = adminService.storeListAll(page, size);

        return ResponseEntity.ok(new BaseResponse(BaseResponseStatus.SUCCESS, response));
    }

    // 식당 상세 조회
    @GetMapping("/store/{storeIdx}")
    public ResponseEntity<BaseResponse<StoreDto.StoreResponseDto>> getStore(@PathVariable Long storeIdx) {
        StoreDto.StoreResponseDto response = adminService.getStore(storeIdx);

        return ResponseEntity.ok(new BaseResponse(BaseResponseStatus.SUCCESS, response));
    }

    // 식당 등록 수락
    @PatchMapping("/store/{storeIdx}")
    public ResponseEntity<BaseResponse<StoreDto.StoreResponseDto>> updateStoreAllowed(
            @PathVariable Long storeIdx,
            @RequestBody StoreDto.UpdateAllowedRequestDto requestDto) {

        StoreDto.StoreResponseDto response = adminService.updateStoreAllowed(storeIdx, requestDto.getAllowed());

        return ResponseEntity.ok(new BaseResponse<>(BaseResponseStatus.SUCCESS, response));
    }
}
