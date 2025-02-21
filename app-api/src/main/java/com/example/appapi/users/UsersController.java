package com.example.appapi.users;

import com.example.appapi.users.model.UsersDto;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RequiredArgsConstructor
@RestController
@RequestMapping("/app/users")
public class UsersController {
    private final UsersService usersService;
    private final UsersRepository usersRepository;

    @PostMapping("/signup")
//    public void signup(@Valid @RequestBody UsersDto.SignupRequest dto) {
//        usersService.signup(dto);
//    }

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

}
