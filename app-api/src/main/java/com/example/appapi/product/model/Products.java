package com.example.appapi.product.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Builder
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class Products {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idx;

    private String name;
    private String description;
    private int price;
    private int stock;
    
}
