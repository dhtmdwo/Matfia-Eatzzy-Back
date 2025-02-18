package com.example.appapi.likes;

import com.example.appapi.likes.model.Likes;
import com.example.appapi.likes.model.LikesDto;
import com.example.appapi.likes.model.Likes;
import com.example.appapi.likes.model.LikesDto;
import com.example.appapi.likes.model.Likes;
import com.example.appapi.likes.model.LikesDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class LikesService {
    private final LikesRepository likesRepository;
    public void register(LikesDto.CourseRegister dto) {
        Likes like = likesRepository.save(dto.toEntity());
    }

    public List<LikesDto.LikesResponse> list() {
        List<Likes> likesList = likesRepository.findAll();

        return likesList.stream().map(LikesDto.LikesResponse::from).collect(Collectors.toList());
    }

    public LikesDto.LikesResponse read(Long LlkesIdx) {
        Likes Likes = likesRepository.findById(LlkesIdx).orElseThrow();
        return LikesDto.LikesResponse.from(Likes);
    }
}
