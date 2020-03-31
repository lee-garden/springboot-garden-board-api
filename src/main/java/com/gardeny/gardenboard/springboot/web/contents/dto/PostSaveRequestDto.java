package com.gardeny.gardenboard.springboot.web.contents.dto;

import com.gardeny.gardenboard.springboot.domain.account.User;
import com.gardeny.gardenboard.springboot.domain.contents.Category;
import com.gardeny.gardenboard.springboot.domain.contents.Post;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class PostSaveRequestDto {
    private String title;
    private Category category;
    private String content;
    private User user;

    @Builder
    public PostSaveRequestDto(String title, Category category, String content, User user) {
        this.title = title;
        this.category = category;
        this.content = content;
        this.user = user;
    }

    public Post toEntity() {
        return Post.builder()
                .title(title)
                .category(category)
                .content(content)
                .user(user)
                .build();
    }

}
