package com.gardeny.gardenboard.springboot.web.contents;

import com.gardeny.gardenboard.springboot.service.contents.PostService;
import com.gardeny.gardenboard.springboot.web.contents.dto.PostListResponseDto;
import com.gardeny.gardenboard.springboot.web.contents.dto.PostSaveRequestDto;
import com.gardeny.gardenboard.springboot.web.contents.dto.PostUpdateRequestDto;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RestController
public class PostApiController {

    private final PostService postService;

    @ApiOperation(value = "포스트 생성 API")
    @PostMapping("/api/v1/post")
    public Long save(@ApiParam(value = "category : NORMAL - > 일반 게시판", required = false)
                     @RequestBody PostSaveRequestDto requestDto) {
        return postService.save(requestDto);
    }

    @ApiOperation(value = "포스트 리스트 API")
    @GetMapping("/api/v1/post")
    public List<PostListResponseDto> list() {
        return postService.findAllDesc();
    }

    @ApiOperation(value = "포스트 수정 API")
    @PutMapping("api/v1/post/{id}")
    public Long update(@PathVariable Long id, @RequestBody PostUpdateRequestDto requestDto) {
        return postService.update(id, requestDto);
    }

    @ApiOperation(value = "포스트 삭제 API")
    @DeleteMapping("api/v1/post/{id}")
    public Long delete(@PathVariable Long id) {
        return postService.delete(id);
    }

}
