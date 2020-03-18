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
    public Long signup(SingUpRequestDto requestDto) {
        return userRepository.save(requestDto.toEntity()).getId();
    }
}
