package com.gardeny.gardenboard.springboot.service.account;

import com.gardeny.gardenboard.springboot.config.exception.ExceptionResponse;
import com.gardeny.gardenboard.springboot.config.exception.SignInFailedException;
import com.gardeny.gardenboard.springboot.config.security.JwtTokenProvider;
import com.gardeny.gardenboard.springboot.domain.account.User;
import com.gardeny.gardenboard.springboot.domain.account.UserRepository;
import com.gardeny.gardenboard.springboot.web.account.dto.SignInRequestDto;
import com.gardeny.gardenboard.springboot.web.account.dto.SingUpRequestDto;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityNotFoundException;

@RequiredArgsConstructor
@Service
public class UserService implements UserDetailsService {

    private final UserRepository userRepository;
    private final JwtTokenProvider jwtTokenProvider;
    private final PasswordEncoder passwordEncoder;

    @Override
    public UserDetails loadUserByUsername(String username){
        return userRepository.findByUsername(username).orElseThrow(EntityNotFoundException::new);
    }

    @Transactional
    public Long signup(SingUpRequestDto requestDto) {
        requestDto.setPassword(passwordEncoder.encode(requestDto.getPassword()));
        return userRepository.save(requestDto.toEntity()).getId();
    }

    @Transactional
    public String signin(SignInRequestDto requestDto) {
        User user = userRepository.findByUsername(requestDto.getUsername()).orElseThrow(SignInFailedException::new);

        if(!passwordEncoder.matches(requestDto.getPassword(), user.getPassword())) {
            throw new SignInFailedException();
        }

        // todo : 토큰 유효성 검사 및 기한 지난 토큰 재 발급 처리

        return jwtTokenProvider.createToken(String.valueOf(user.getId()), user.getRoles());
    }
}
