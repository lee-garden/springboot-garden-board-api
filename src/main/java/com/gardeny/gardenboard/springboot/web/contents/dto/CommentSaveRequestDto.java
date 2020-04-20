package com.gardeny.gardenboard.springboot.web.contents.dto;

import com.gardeny.gardenboard.springboot.domain.account.User;
import com.gardeny.gardenboard.springboot.domain.comment.Comment;
import com.gardeny.gardenboard.springboot.domain.contents.Post;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class CommentSaveRequestDto {
    private String content;
    private User user;
    private Post post;
    private Comment parentComment;

    @Builder
    public CommentSaveRequestDto(String content, User user, Post post, Comment parentComment) {
        this.content = content;
        this.user = user;
        this.post = post;
        this.parentComment = parentComment;
    }

    public Comment toEntity() {
        return Comment.builder()
                .content(content)
                .user(user)
                .post(post)
                .parentComment(parentComment)
                .build();
    }

}
