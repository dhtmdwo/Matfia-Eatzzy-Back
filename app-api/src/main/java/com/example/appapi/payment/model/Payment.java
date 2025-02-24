package com.example.appapi.payment.model;

import com.example.appapi.orders.model.Orders;
import com.example.appapi.paymentmethod.model.PaymentMethod;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Entity
public class Payment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idx;
    private String status;
    int price;

    @OneToOne
    @JoinColumn(name = "PaymentMethod_idx")
    private PaymentMethod paymentMethod;

    @OneToOne(mappedBy = "payment")
    private Orders orders;

}
