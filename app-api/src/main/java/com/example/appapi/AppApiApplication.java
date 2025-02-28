package com.example.appapi;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication(scanBasePackages = {"com.example.appapi", "com.example.common"})
public class AppApiApplication {

    public static void main(String[] args) {
        SpringApplication.run(AppApiApplication.class, args);
    }

}
