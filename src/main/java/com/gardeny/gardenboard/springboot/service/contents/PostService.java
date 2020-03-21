package com.gardeny.gardenboard.springboot.service.contents;

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
    public Long save(PostSaveRequestDto requestDto) {
        return postRepository.save(requestDto.toEntity()).getId();
    }

    @Transactional(readOnly = true)
    public List<PostListResponseDto> findAllDesc() {
        return postRepository.findAllDesc().stream()
                .map(PostListResponseDto::new)
                .collect(Collectors.toList());
    }

    @Transactional
    public Long update(Long id, PostUpdateRequestDto requestDto) {
        Post post = postRepository.findById(id).orElseThrow(() ->
                new IllegalArgumentException("해당 포스트가 존재하지 않습니다. id : " + id));

        post.update(requestDto.getTitle(), requestDto.getContent());

        return post.getId();
    }

    @Transactional
    public Long delete(Long id) {
        Post post = postRepository.findById(id).orElseThrow(() ->
                new IllegalArgumentException("해당 포스트가 존재하지 않습니다. id : " + id));

        // todo : is_removed True 처리

        return post.getId();
    }
}
