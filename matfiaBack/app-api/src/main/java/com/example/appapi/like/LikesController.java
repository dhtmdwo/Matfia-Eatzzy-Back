package com.example.appapi.like;

import com.example.appapi.like.model.LikesDto;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RestController
@RequestMapping("/likes")
public class LikesController {
    private final LikesService likeService;
    @PostMapping("/register")
    public String register(@RequestBody LikesDto.CourseRegister dto) {
        likeService.register(dto);
        return "좋아요 등록 완료";
    }


}
