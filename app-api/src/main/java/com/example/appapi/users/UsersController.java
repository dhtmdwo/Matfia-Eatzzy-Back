package com.example.appapi.users;

import com.example.appapi.users.model.UsersDto;
import com.example.appapi.utils.JwtUtil;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

@RequiredArgsConstructor
@RestController
@RequestMapping("/app/users")
public class UsersController {
    private final UsersService usersService;
    private final UsersRepository usersRepository;
    private final KakaoService kakaoService;

    @PostMapping("/signup")
    public ResponseEntity<?> signup(@Valid @RequestBody UsersDto.SignupRequest dto, BindingResult bindingResult) {
        // 유효성 검사 실패 시 에러 메시지 처리
        if (bindingResult.hasErrors()) {
            Map<String, String> errors = new HashMap<>();
            bindingResult.getFieldErrors().forEach(error ->
                    errors.put(error.getField(), error.getDefaultMessage())
            );
            return ResponseEntity.badRequest().body(errors);
        }

        Object response = usersService.signup(dto);

        return ResponseEntity.ok(response);
    }

    @GetMapping("/info/{userIdx}")
    public ResponseEntity<UsersDto.UserResponse> info(@PathVariable Long userIdx) {
        UsersDto.UserResponse response = usersService.read(userIdx);

        return ResponseEntity.ok(response);
    }
}
