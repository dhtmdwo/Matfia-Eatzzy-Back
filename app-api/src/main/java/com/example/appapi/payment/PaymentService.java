package com.example.appapi.payment;

import com.example.appapi.payment.PaymentRepository;
import com.example.appapi.payment.model.Payment;
import com.example.appapi.payment.model.PaymentDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class PaymentService {
    private final PaymentRepository paymentRepository;
    public void register(PaymentDto.PaymentRegister dto) {
        Payment payment = paymentRepository.save(dto.toEntity());
    }

    public List<PaymentDto.PaymentResponse> list() {
        List<Payment> paymentList = paymentRepository.findAll();

        return paymentList.stream().map(PaymentDto.PaymentResponse::from).collect(Collectors.toList());
    }

    public PaymentDto.PaymentResponse read(Long paymentIdx) {
        Payment payment = paymentRepository.findById(paymentIdx).orElseThrow();
        return PaymentDto.PaymentResponse.from(payment);
    }

}