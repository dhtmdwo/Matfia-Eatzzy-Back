package com.example.appapi.reviews;

import com.example.appapi.reviews.model.Reviews;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ReviewsRepository extends JpaRepository<Reviews, Long> {

}