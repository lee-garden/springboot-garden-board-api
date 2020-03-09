package com.gardeny.gardenboard.springboot.web.dto;

import com.gardeny.gardenboard.springboot.domain.contents.Category;
import com.gardeny.gardenboard.springboot.domain.contents.Post;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class PostSaveRequestDto {
    private String title;
    private Category category;
    private String content;
    private String author;

    @Builder
    public PostSaveRequestDto(String title, Category category, String content, String author) {
        this.title = title;
        this.category = category;
        this.content = content;
        this.author = author;
    }

    public Post toEntity() {
        return Post.builder()
                .title(title)
                .category(category)
                .content(content)
                .author(author)
                .build();
    }

}
