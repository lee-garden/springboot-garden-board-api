package com.gardeny.gardenboard.springboot.web.contents.dto;


import com.gardeny.gardenboard.springboot.domain.contents.Category;
import com.gardeny.gardenboard.springboot.domain.contents.Post;
import lombok.Getter;

@Getter
public class PostListResponseDto {
    private Long id;
    private String title;
    private Category category;
    private String content;
    private String author;

    public PostListResponseDto(Post entity) {
        this.id = entity.getId();
        this.title = entity.getTitle();
        this.category = entity.getCategory();
        this.content = entity.getContent();
        this.author = entity.getAuthor();
    }
}
