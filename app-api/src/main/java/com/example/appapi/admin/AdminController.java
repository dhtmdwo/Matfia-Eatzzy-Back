package com.example.appapi.admin;

import com.example.appapi.category.model.Category;
import com.example.appapi.category.model.CategoryDto;
import com.example.appapi.store.StoreService;
import com.example.appapi.store.model.StoreDto;
import com.example.common.BaseResponse;
import com.example.common.BaseResponseStatus;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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

    // 식당 등록 수락 & 카테고리 배정 수정
    @PatchMapping("/store/{storeIdx}")
    public ResponseEntity<BaseResponse<StoreDto.StoreResponseDto>> updateStoreStatus(
            @PathVariable Long storeIdx,
            @RequestBody StoreDto.UpdateStoreStatusDto requestDto) {

        StoreDto.StoreResponseDto response = adminService.updateStoreStatus(storeIdx, requestDto.getCategory(), requestDto.getAllowed());

        return ResponseEntity.ok(new BaseResponse<>(BaseResponseStatus.SUCCESS, response));
    }

    // 카테고리 수정
    @PatchMapping("/category/{categoryIdx}")
    public ResponseEntity<BaseResponse<CategoryDto.CategoryResponseDto>> updateCategoryName(
            @PathVariable Long categoryIdx,
            @RequestBody CategoryDto.UpdateCategoryRequestDto requestDto) {

        CategoryDto.CategoryResponseDto response = adminService.updateCategoryName(categoryIdx, requestDto.getName());

        return ResponseEntity.ok(new BaseResponse<>(BaseResponseStatus.SUCCESS, response));
    }

    // 카테고리 삭제
    @DeleteMapping("/category/{categoryIdx}")
    public ResponseEntity<BaseResponse<Void>> deleteCategoryWithChildren(@PathVariable Long categoryIdx) {
        // 서비스에서 반환되는 BaseResponse를 그대로 응답으로 반환
        BaseResponse<Void> response = adminService.deleteCategoryWithChildren(categoryIdx);

        return ResponseEntity.ok(response);
    }


}
