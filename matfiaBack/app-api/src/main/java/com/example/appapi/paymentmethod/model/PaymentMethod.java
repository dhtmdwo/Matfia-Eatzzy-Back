<<<<<<<< HEAD:matfiaBack/app-api/src/main/java/com/example/appapi/orders/model/Orders.java
package com.example.appapi.orders.model;
========
package com.example.appapi.paymentmethod.model;
>>>>>>>> origin/main:matfiaBack/app-api/src/main/java/com/example/appapi/paymentmethod/model/PaymentMethod.java

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Entity
public class PaymentMethod {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idx;
<<<<<<<< HEAD:matfiaBack/app-api/src/main/java/com/example/appapi/orders/model/Orders.java
    private int quantity;
    private int price;
    private String message;
    private String status;
}
========
    private String name;
}
>>>>>>>> origin/main:matfiaBack/app-api/src/main/java/com/example/appapi/paymentmethod/model/PaymentMethod.java
