package com.gardeny.gardenboard.springboot.web.account.dto;

import com.gardeny.gardenboard.springboot.domain.account.User;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.util.Collections;

@Setter
@Getter
public class SingUpRequestDto {
    private String username;
    private String password;
    private String name;
    private String phone;

    @Builder
    public SingUpRequestDto(String username, String password, String name, String phone){
        this.username = username;
        this.password = password;
        this.name = name;
        this.phone = phone;
    }

    public User toEntity() {
        return User.builder()
                .username(username)
                .password(password)
                .name(name)
                .phone(phone)
                .roles(Collections.singletonList("USER"))
                .build();
    }
}
