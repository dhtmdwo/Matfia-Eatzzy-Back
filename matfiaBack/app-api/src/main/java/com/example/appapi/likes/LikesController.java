package com.example.appapi.likes;

import com.example.appapi.likes.model.LikesDto;
import com.example.appapi.likes.model.LikesDto;
import com.example.appapi.likes.model.LikesDto;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/likes")
public class LikesController {
    private final LikesService likesService;
    @PostMapping("/register")
    public String register(@RequestBody LikesDto.CourseRegister dto) {
        likesService.register(dto);
        return "좋아요 등록 완료";
    }
    @GetMapping("/list")
    public ResponseEntity<List<LikesDto.LikesResponse>> list() {
        List<LikesDto.LikesResponse> response = likesService.list();

        return ResponseEntity.ok(response);
    }

    @GetMapping("/{likesIdx}")
    public ResponseEntity<LikesDto.LikesResponse> read(@PathVariable Long likesIdx) {
        LikesDto.LikesResponse response = likesService.read(likesIdx);

        return ResponseEntity.ok(response);
    }


}
