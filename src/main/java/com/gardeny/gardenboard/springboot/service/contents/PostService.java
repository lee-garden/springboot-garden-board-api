package com.gardeny.gardenboard.springboot.service.contents;

import com.gardeny.gardenboard.springboot.domain.account.User;
import com.gardeny.gardenboard.springboot.domain.contents.Post;
import com.gardeny.gardenboard.springboot.domain.contents.PostRepository;
import com.gardeny.gardenboard.springboot.web.contents.dto.PostListResponseDto;
import com.gardeny.gardenboard.springboot.web.contents.dto.PostSaveRequestDto;
import com.gardeny.gardenboard.springboot.web.contents.dto.PostUpdateRequestDto;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityNotFoundException;
import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class PostService {
    private final PostRepository postRepository;

    @Transactional
    public Long save(PostSaveRequestDto requestDto) {
        User user = (User)SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        requestDto.setUser(user);
        return postRepository.save(requestDto.toEntity()).getId();
    }

    @Transactional(readOnly = true)
    public List<PostListResponseDto> findAllDesc() {
        return postRepository.findAllByIsRemovedFalseOrderByIdDesc().stream()
                .map(PostListResponseDto::new)
                .collect(Collectors.toList());
    }

    @Transactional(readOnly = true)
    public List<PostListResponseDto> findMyPost() {
        User user = (User)SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        return postRepository.findByUser_idAndIsRemovedFalseOrderByCreatedDesc(user.getId()).stream()
                .map(PostListResponseDto::new)
                .collect(Collectors.toList());
    }

    @Transactional
    public Long update(Long id, PostUpdateRequestDto requestDto) {
        Post post = postRepository.findById(id).orElseThrow(EntityNotFoundException::new);

        post.update(requestDto.getTitle(), requestDto.getContent());

        return post.getId();
    }

    @Transactional
    public Long delete(Long id) {
        Post post = postRepository.findById(id).orElseThrow(EntityNotFoundException::new);

        post.delete();
        postRepository.save(post);

        return post.getId();
    }

    @Transactional
    public boolean like(Long id) {
        User user = (User)SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        Post post = postRepository.findById(id).orElseThrow(EntityNotFoundException::new);
        boolean isLike;

        if (post.getLikes().contains(user)) {
            post.getLikes().remove(user);
            isLike = false;
        } else {
            post.getLikes().add(user);
            isLike = true;
        }
        postRepository.save(post);

        return isLike;
    }
}
