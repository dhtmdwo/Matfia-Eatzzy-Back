package com.example.appapi.delivery.model;

import com.example.appapi.orders.model.Orders;
import jakarta.persistence.*;

@Entity
public class Delivery {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idx;
    private String delivery_status;
    private String courier_company;
    private int tracking_number;

    @OneToOne
    @JoinColumn(name = "order_idx")
    private Orders orders;
}
