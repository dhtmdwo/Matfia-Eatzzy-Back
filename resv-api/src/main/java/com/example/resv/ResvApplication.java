package com.example.resv;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@EntityScan(basePackages = {
        "com.example.appapi.store.model",
        "com.example.appapi.category.model",
        "com.example.appapi.users.model",
        "com.example.appapi.likes.model",
        "com.example.appapi.store.images.model",
        "com.example.appapi.store.review.model",
        "com.example.resv.resv.model"})
@EnableJpaRepositories(basePackages = {
        "com.example.appapi.store",
        "com.example.resv.resv"})
@SpringBootApplication
public class ResvApplication {

    public static void main(String[] args) {
        SpringApplication.run(ResvApplication.class, args);
    }

}
