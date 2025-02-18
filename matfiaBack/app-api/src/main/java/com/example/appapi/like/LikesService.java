package com.example.appapi.like;

import com.example.appapi.like.model.Likes;
import com.example.appapi.like.model.LikesDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class LikesService {
    private final LikesRepository likeRepository;
    public void register(LikesDto.CourseRegister dto) {
        Likes like = likeRepository.save(dto.toEntity());
    }
}
