package com.example.appapi.likes;

import com.example.appapi.likes.model.Likes;
import com.example.appapi.likes.model.LikesDto;
import com.example.appapi.store.model.Store;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class LikesService {
    private final LikesRepository likesRepository;
    public void register(LikesDto.LikeRegister dto) {
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

    public List<LikesDto.StoreLikesResponse> storeList(Long idx) {
        List<Likes> likes = likesRepository.findLikesByUserId(idx);

        List<LikesDto.StoreLikesResponse> responseList = new ArrayList<>();

        for (Likes like : likes) {
            Store store = like.getStore();
            LikesDto.StoreLikesResponse response = LikesDto.StoreLikesResponse.from(store);
            responseList.add(response);
        }

        return responseList;
    } // 마이페이지 좋아요 한 식당 내역 보기

    public void deleteLikes(Long idx) {
        likesRepository.findById(idx).ifPresentOrElse(
                likesRepository::delete,
                () -> {
                    throw new IllegalArgumentException("해당 식당이 존재하지 않습니다");
                }
        );// idx 값으로 검색후 있으면 삭제 없으면 예외처리
    } // 마이페이지 클라이언트 식당 좋아요 삭제
    
    
}
