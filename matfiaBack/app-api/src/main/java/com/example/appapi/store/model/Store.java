package com.example.appapi.store.model;

import jakarta.persistence.*;
import lombok.*;

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

    private Long userIdx;  // user_idx (FK)

    private Long categoryIdx;  // category_idx (FK)
}
