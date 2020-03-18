package com.gardeny.gardenboard.springboot.service.account;

import com.gardeny.gardenboard.springboot.config.response.Response;
import com.gardeny.gardenboard.springboot.domain.account.User;
import com.gardeny.gardenboard.springboot.domain.account.UserRepository;
import com.gardeny.gardenboard.springboot.web.account.dto.SingUpRequestDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Service
public class UserService {

    private final UserRepository userRepository;

    @Transactional
    public Response signup(SingUpRequestDto requestDto) {
        User user = userRepository.save(requestDto.toEntity());
        System.out.println("");
        return Response.builder()
                .success(true)
                .status_code(201)
                .msg("회원가입 성공")
                .build();
    }
}
