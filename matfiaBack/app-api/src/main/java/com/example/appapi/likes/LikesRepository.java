package com.example.appapi.likes;

import com.example.appapi.likes.model.Likes;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LikesRepository extends JpaRepository<Likes, Long> {
}
