package com.example.appapi.paymentmethod;

import com.example.appapi.payment.model.Payment;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PaymentMethodRepository extends JpaRepository<Payment, Long> {
}
