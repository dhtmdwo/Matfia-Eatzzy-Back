package com.example.appapi.users;

import com.example.appapi.handler.OAuth2SuccessHandler;
import com.example.appapi.users.model.UsersDto;
import com.example.appapi.utils.JwtUtil;
import io.swagger.v3.oas.annotations.Operation;
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
import java.util.List;
import java.util.Map;

@RequiredArgsConstructor
@RestController
@RequestMapping("/app/users")
public class UsersController {
    private final UsersService usersService;
    private final KakaoService kakaoService;

    @PostMapping("/signup")
    @Operation(summary = "회원 가입")
    public ResponseEntity<?> signup(@Valid @RequestBody UsersDto.SignupRequest dto, BindingResult bindingResult) {
        /* 유효성 검사 실패 시 에러 메시지 처리 */
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

    @Operation(summary = "회원 정보 조회")
    @GetMapping("/info/{userIdx}")
    public ResponseEntity<UsersDto.UserResponse> info(@PathVariable Long userIdx) {
        UsersDto.UserResponse response = usersService.read(userIdx);

        return ResponseEntity.ok(response);
    }

    @Operation(summary = "회원 리스트 조회")
    @GetMapping("/list")
    public ResponseEntity<List<UsersDto.UserResponse>> list() {
        List<UsersDto.UserResponse> response = usersService.getList();

        return ResponseEntity.ok(response);
    }

    @GetMapping("/validate")
    public void validate(HttpServletRequest request) {
            String token = null;

            // 쿠키에서 토큰 추출
            Cookie[] cookies = request.getCookies();
            if (cookies != null) {
                for (Cookie cookie : cookies) {
                    if ("ATOKEN".equals(cookie.getName())) {
                        token = cookie.getValue();
                        break;
                    }
                }
            }

            // 토큰이 없는 경우 false 반환
            if (token == null) {
                System.out.println("ATOKEN 쿠키가 없습니다.");
//                return false;
            }

            // 토큰 검증
        System.out.println(JwtUtil.validate(token));
    }

    @GetMapping("/kakao/code")
    protected void code(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String code = request.getParameter("code");

        if (code == null) {
            response.sendError(HttpServletResponse.SC_BAD_REQUEST, "Authorization code is missing.");
            return;
        }

        try {
            String kakaoId = kakaoService.kakaoLogin(code);

            if (kakaoId != null) {
                OAuth2SuccessHandler.onAuthenticationSuccess(request, response, kakaoId);
            } else {
                response.sendError(HttpServletResponse.SC_UNAUTHORIZED, "Kakao login failed.");
            }
        } catch (Exception e) {
            e.printStackTrace();
            response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "An error occurred during login.");
        }
    }

}
