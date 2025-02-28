package com.example.appapi.orders.model;

import com.example.appapi.product.model.Products;
import com.example.appapi.users.model.Users;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Builder
@Entity
public class OrderProducts {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idx;
    private int quantity;

    @ManyToOne
    @JoinColumn(name = "orders_idx")
    private Orders orders;

    @ManyToOne
    @JoinColumn(name = "product_idx")
    private Products products;
}
