package com.gardeny.gardenboard.springboot.web.account;

import com.gardeny.gardenboard.springboot.service.account.UserService;
import com.gardeny.gardenboard.springboot.web.account.dto.SignInRequestDto;
import com.gardeny.gardenboard.springboot.web.account.dto.SingUpRequestDto;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;


@RequiredArgsConstructor
@RestController
public class UserApiController {

    private final UserService userService;

    @ApiOperation(value = "회원 가입 API")
    @PostMapping("/api/v1/signup")
    public Long signup(@RequestBody SingUpRequestDto requestDto) {
        return userService.signup(requestDto);
    }

    @ApiOperation(value = "로그인 API")
    @PostMapping("/api/v1/signin")
    public String signin(@RequestBody SignInRequestDto requestDto) {
        return userService.signin(requestDto);
    }
}
