package com.example.appapi.likes;

import com.example.appapi.likes.model.Likes;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface LikesRepository extends JpaRepository<Likes, Long> {
    @Query("SELECT DISTINCT l FROM Likes l " +
            "JOIN FETCH  l.store s " +
            "LEFT JOIN FETCH s.images si " +
            "WHERE  l.users.idx = :userId")
    List<Likes> findLikesByUserId(@Param("userId") Long userId);
}
