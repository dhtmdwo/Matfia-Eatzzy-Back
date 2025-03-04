package com.example.appapi.orderProducts;

import com.example.appapi.orders.model.Orders;
import com.example.appapi.product.model.Products;
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
    @JoinColumn(name = "products_idx")
    private Products products;
}
