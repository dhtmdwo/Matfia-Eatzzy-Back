package com.example.appapi.menus;

import com.example.appapi.menus.model.MenusDto;
import com.example.appapi.users.model.Users;
import com.example.common.BaseResponse;
import com.example.common.BaseResponseStatus;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
@RequestMapping("/app/menu")
public class MenusController {
    private final MenusService menusService;
    @PostMapping("/create")
    public ResponseEntity<BaseResponse<MenusDto.MenusResponseDto>> createMenu(
            @AuthenticationPrincipal Users user,
            @RequestBody MenusDto.CreateMenuRequestDto dto){
        MenusDto.MenusResponseDto resp = menusService.create(user, dto);

        return ResponseEntity.ok(new BaseResponse(BaseResponseStatus.SUCCESS, resp));
    }
}
