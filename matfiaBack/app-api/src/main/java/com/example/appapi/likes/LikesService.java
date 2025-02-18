package com.example.appapi.likes;

import com.example.appapi.likes.model.Likes;
import com.example.appapi.likes.model.LikesDto;
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
