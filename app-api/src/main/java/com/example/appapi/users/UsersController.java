package com.example.appapi.users;

import com.example.appapi.handler.OAuth2SuccessHandler;
import com.example.appapi.users.model.Users;
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
import java.util.List;
import java.util.Map;

@RequiredArgsConstructor
@RestController
@RequestMapping("/app/users")
public class UsersController {
    private final UsersService usersService;
    private final UsersRepository usersRepository;
    //private final KakaoService kakaoService;

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
        String kakaoId = null;
        kakaoId = kakaoService.kakaoLogin(code);

        try {
            if(kakaoId != null) {
                // 로그인 처리
                try {
                    // 사용자 회원 여부 확인
                    boolean isUser = usersRepository.existsByUserId(kakaoId);
                    if (isUser == false) {
                        // 사용자 회원 가입 처리
                        Users user = new Users();
                        user.setUserId("kakao" + kakaoId);
                        user.setPassword("kakao@" + kakaoId);
                        usersRepository.save(user);
                    }

                    // JWT 발급
                    String token = JwtUtil.generateToken("kakao" + kakaoId);

                    // JWT 반환
//                    oAuth2SuccessHandler.onAuthenticationSuccess(request, response, token);

                } catch (Exception e) {
                    System.out.println("실패");
                }
            } else {
                System.out.println("실패");
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
