package com.gardeny.gardenboard.springboot.web.contents;

import com.gardeny.gardenboard.springboot.service.contents.PostService;
import com.gardeny.gardenboard.springboot.web.contents.dto.*;
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
    public Long save(
                     @ApiParam(value = "category : NORMAL | MARKET | INFO", required = false)
                     @RequestBody PostSaveRequestDto requestDto) {
        return postService.save(requestDto);
    }

    @ApiOperation(value = "포스트 리스트 API")
    @GetMapping("/api/v1/post")
    public List<PostListResponseDto> postList() {
        return postService.findAllDesc();
    }

    @ApiOperation(value = "내가 쓴 포스트 리스트 API")
    @GetMapping("/api/v1/post/my")
    public List<PostListResponseDto> myPostList() { return postService.findMyPost(); }

    @ApiOperation(value = "포스트 디테일 API")
    @GetMapping("/api/v1/post/{id}")
    public PostRetrieveResponseDto postRetrieve(@PathVariable Long id) {return postService.postRetrieve(id); }

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

    @ApiOperation(value = "포스트 좋아요 API")
    @PostMapping("api/v1/post/{id}/like")
    public boolean postLike(@PathVariable Long id) { return postService.like(id);}

    @ApiOperation(value = "포스트 댓글 API")
    @PostMapping("api/v1/post/{id}/comment")
    public Long createComment(@PathVariable Long id, @RequestBody CommentSaveRequestDto requestDto) {
        return postService.createComment(id, requestDto);
    }

    @ApiOperation(value = "포스트 댓글 리스트 API")
    @GetMapping("api/v1/post/{id}/comments")
    public List<CommentListResponseDto> getComments(@PathVariable Long id) {
        return postService.getComments(id);
    }

}
