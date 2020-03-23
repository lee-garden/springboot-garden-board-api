package com.gardeny.gardenboard.springboot.web.account.dto;

import lombok.Builder;
import lombok.Getter;

@Getter
public class SignInRequestDto {
    private String username;
    private String password;

    @Builder
    public SignInRequestDto(String username, String password){
        this.username = username;
        this.password = password;
    }
}
