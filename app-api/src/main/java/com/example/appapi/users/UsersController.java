package com.example.appapi.users;

import com.example.appapi.users.model.UsersDto;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
@RequestMapping("/app/users")
public class UsersController {
    private final UsersService usersService;

    @PostMapping("/signup")
    public void signup(@Valid @RequestBody UsersDto.SignupRequest dto) {
        usersService.signup(dto);
    }

    //    public ResponseEntity<UsersDto.SignupResponse> signup(@Valid @RequestBody UsersDto.SignupRequest dto) {
//
//        UsersDto.SignupResponse response = usersService.signup(dto);
//
//        return ResponseEntity.ok(response);
//    }
}
