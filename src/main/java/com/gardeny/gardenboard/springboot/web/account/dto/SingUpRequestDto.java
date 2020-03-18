package com.gardeny.gardenboard.springboot.web.account.dto;

import com.gardeny.gardenboard.springboot.domain.account.User;
import com.gardeny.gardenboard.springboot.domain.contents.Post;
import lombok.Builder;
import lombok.Getter;

@Getter
public class SingUpRequestDto {
    private String email;
    private String password;
    private String name;
    private String phone;

    @Builder
    public SingUpRequestDto(String email, String password, String name, String phone){
        this.email = email;
        this.password = password;
        this.name = name;
        this.phone = phone;
    }

    public User toEntity() {
        return User.builder()
                .email(email)
                .password(password)
                .name(name)
                .phone(phone)
                .build();
    }
}
