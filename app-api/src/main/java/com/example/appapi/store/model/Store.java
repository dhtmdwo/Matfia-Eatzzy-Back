package com.example.appapi.store.model;

import com.example.appapi.product.images.model.ProductsImages;
import com.example.appapi.store.images.model.StoreImages;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.BatchSize;

import java.time.LocalDateTime;
import java.time.DayOfWeek;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
@Entity
public class Store {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idx;
    private String name;
    private String description;

    private String address;
    private String shortAddress;
    private String callNumber;
    private String openingHours;

    private LocalTime startTime;
    private LocalTime endTime;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private AllowedStatus allowed;  // ENUM ('Yes', 'No', 'Waiting')

    @OneToMany(mappedBy = "store")
    private List<StoreClosedDay> closedDayList = new ArrayList<>();  // store_closed_days (FK)

    @BatchSize(size = 6)
    @OneToMany(mappedBy = "store")
    private List<StoreImages> images;



    private Long userIdx;  // user_idx (FK)

    private Long categoryIdx;  // category_idx (FK)




}
