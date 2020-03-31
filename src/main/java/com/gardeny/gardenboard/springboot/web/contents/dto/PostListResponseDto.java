package com.gardeny.gardenboard.springboot.web.contents.dto;


import com.gardeny.gardenboard.springboot.domain.account.User;
import com.gardeny.gardenboard.springboot.domain.contents.Category;
import com.gardeny.gardenboard.springboot.domain.contents.Post;
import lombok.Getter;

@Getter
public class PostListResponseDto {
    private Long id;
    private String title;
    private Long user_id;
    private String user;
    private Category category;
    private String content;

    public PostListResponseDto(Post entity) {
        this.id = entity.getId();
        this.title = entity.getTitle();
        this.user_id = entity.getUser().getId();
        this.user = entity.getUser().getName();
        this.category = entity.getCategory();
        this.content = entity.getContent();
    }
}
