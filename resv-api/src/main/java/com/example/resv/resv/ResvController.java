package com.example.resv.resv;

import com.example.appapi.users.model.Users;
import com.example.resv.resv.model.ResvDto;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
@RequestMapping("/resv")
public class ResvController {
    private final ResvService resvService;

    @PostMapping("/create")
    public ResponseEntity<ResvDto.ResvResponse> create(@AuthenticationPrincipal Users user, @RequestBody ResvDto.CreateResvRequest dto) {
        ResvDto.ResvResponse resv = resvService.create(dto, user);

        return ResponseEntity.ok(resv);
    }
}
