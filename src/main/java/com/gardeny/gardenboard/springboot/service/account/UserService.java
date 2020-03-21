package com.gardeny.gardenboard.springboot.service.account;

import com.gardeny.gardenboard.springboot.domain.account.UserRepository;
import com.gardeny.gardenboard.springboot.web.account.dto.SingUpRequestDto;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Service
public class UserService implements UserDetailsService {

    private final UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username){
        return userRepository.findByUsername(username).orElseThrow(() ->
                new UsernameNotFoundException("해당 유저가 존재 하지 않습니다."));
    }

    @Transactional
    public Long signup(SingUpRequestDto requestDto) {
        return userRepository.save(requestDto.toEntity()).getId();
    }
}
