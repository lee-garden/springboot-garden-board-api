package com.gardeny.gardenboard.springboot.web;

import com.gardeny.gardenboard.springboot.service.contents.PostService;
import com.gardeny.gardenboard.springboot.web.dto.PostSaveRequestDto;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

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

}
