package com.gardeny.gardenboard.springboot.service.contents;

import com.gardeny.gardenboard.springboot.config.response.ListResponse;
import com.gardeny.gardenboard.springboot.config.response.RetrieveResponse;
import com.gardeny.gardenboard.springboot.domain.contents.Post;
import com.gardeny.gardenboard.springboot.domain.contents.PostRepository;
import com.gardeny.gardenboard.springboot.web.contents.dto.PostListResponseDto;
import com.gardeny.gardenboard.springboot.web.contents.dto.PostSaveRequestDto;
import com.gardeny.gardenboard.springboot.web.contents.dto.PostUpdateRequestDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class PostService {
    private final PostRepository postRepository;

    @Transactional
    public RetrieveResponse<Long> save(PostSaveRequestDto requestDto) {
        Long post_id = postRepository.save(requestDto.toEntity()).getId();

        return RetrieveResponse.<Long>builder()
                               .success(true)
                               .status_code(200)
                               .msg("")
                               .data(post_id)
                               .build();
    }

    @Transactional(readOnly = true)
    public ListResponse<PostListResponseDto> findAllDesc() {
        List<PostListResponseDto> posts = postRepository.findAllDesc().stream()
                    .map(PostListResponseDto::new)
                    .collect(Collectors.toList());

        return  ListResponse.<PostListResponseDto>builder()
                            .success(true)
                            .status_code(200)
                            .msg("")
                            .data(posts)
                            .build();
    }

    @Transactional
    public RetrieveResponse<Long> update(Long id, PostUpdateRequestDto requestDto) {
        Post post = postRepository.findById(id).orElseThrow(() ->
                new IllegalArgumentException("해당 포스트가 존재하지 않습니다. id : " + id));

        post.update(requestDto.getTitle(), requestDto.getContent());

        return RetrieveResponse.<Long>builder()
                .success(true)
                .status_code(200)
                .msg("")
                .data(id)
                .build();
    }

    @Transactional
    public RetrieveResponse<Long> delete(Long id) {
        Post post = postRepository.findById(id).orElseThrow(() ->
                new IllegalArgumentException("해당 포스트가 존재하지 않습니다. id : " + id));

        // todo : is_removed True 처리

        return RetrieveResponse.<Long>builder()
                .success(true)
                .status_code(200)
                .msg("")
                .data(id)
                .build();
    }
}
