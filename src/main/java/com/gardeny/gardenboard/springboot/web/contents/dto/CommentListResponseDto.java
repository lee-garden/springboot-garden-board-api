package com.gardeny.gardenboard.springboot.web.contents.dto;

import com.gardeny.gardenboard.springboot.domain.comment.Comment;
import com.gardeny.gardenboard.springboot.domain.contents.Post;
import lombok.Getter;

import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@Getter
public class CommentListResponseDto {
    private Long id;
    private Long post_id;
    private Long user_id;
    private String user;
    private String content;
    private List<CommentListResponseDto> replyCommentListDto;

    public CommentListResponseDto(Comment entity) {
        List<Comment> comments = entity.getChildren();
        Collections.sort(comments, new Comparator<Comment>() {
            @Override
            public int compare(Comment o1, Comment o2) {
                return (int) (o1.getId() - o2.getId());
            }
        });

        this.id = entity.getId();
        this.post_id = entity.getPost().getId();
        this.content = entity.getContent();
        this.user_id = entity.getUser().getId();
        this.user = entity.getUser().getName();
        this.replyCommentListDto = comments.stream()
                .map(CommentListResponseDto::new)
                .collect(Collectors.toList());
    }
}
