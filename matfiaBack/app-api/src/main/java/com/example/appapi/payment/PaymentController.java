package com.example.appapi.payment;

import com.example.appapi.payment.model.PaymentDto;
import lombok.*;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@RestController
@RequestMapping("/payment")
public class PaymentController {
    private final PaymentService paymentService;
    @PostMapping("/register")
    public String register(@RequestBody PaymentDto.PaymentRegister dto) {
        paymentService.register(dto);
        return "결제 등록 완료";
    }

    @GetMapping("/list")
    public ResponseEntity<List<PaymentDto.PaymentResponse>> list() {
        List<PaymentDto.PaymentResponse> response = paymentService.list();

        return ResponseEntity.ok(response);
    }



}
