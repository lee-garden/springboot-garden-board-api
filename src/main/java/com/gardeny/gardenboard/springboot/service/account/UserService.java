package com.gardeny.gardenboard.springboot.service.account;

import com.gardeny.gardenboard.springboot.config.exception.SignInFailedException;
import com.gardeny.gardenboard.springboot.domain.account.User;
import com.gardeny.gardenboard.springboot.domain.account.UserRepository;
import com.gardeny.gardenboard.springboot.web.account.dto.SignInRequestDto;
import com.gardeny.gardenboard.springboot.web.account.dto.SignUpRequestDto;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityNotFoundException;

@RequiredArgsConstructor
@Service
public class UserService implements UserDetailsService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    @Override
    public UserDetails loadUserByUsername(String userID){
        return userRepository.findById(Long.valueOf(userID)).orElseThrow(EntityNotFoundException::new);
    }

    @Transactional
    public Long signup(SignUpRequestDto requestDto) {
        requestDto.setPassword(passwordEncoder.encode(requestDto.getPassword()));
        return userRepository.save(requestDto.toEntity()).getId();
    }

    @Transactional
    public User signin(SignInRequestDto requestDto) {
        User user = userRepository.findByUsername(requestDto.getUsername()).orElseThrow(SignInFailedException::new);

        if(!passwordEncoder.matches(requestDto.getPassword(), user.getPassword())) {
            throw new SignInFailedException();
        }

        // todo : 토큰 유효성 검사 및 기한 지난 토큰 재 발급 처리

        return user;
    }
}
