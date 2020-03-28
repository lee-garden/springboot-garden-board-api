package com.gardeny.gardenboard.springboot.web.account;

import com.gardeny.gardenboard.springboot.domain.account.User;
import com.gardeny.gardenboard.springboot.domain.account.UserRepository;
import com.gardeny.gardenboard.springboot.web.account.dto.SignUpRequestDto;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class UserApiControllerTest {

    @LocalServerPort
    private int port;

    @Autowired
    private TestRestTemplate restTemplate;

    @Autowired
    private UserRepository userRepository;

    private PasswordEncoder passwordEncoder;

    @Before
    public void setUp() throws Exception {
        passwordEncoder = PasswordEncoderFactories.createDelegatingPasswordEncoder();
    }

    @After
    public void tearDown() throws Exception {
        userRepository.deleteAll();
    }

    @Test
    public void Signup_test() throws Exception{
        //given
        String username = "test@test.com";
        String password = "test1234";
        String name = "테스트유저";
        String phone = "01097941234";
        SignUpRequestDto requestDto = SignUpRequestDto.builder()
                                                .username(username)
                                                .password(password)
                                                .name(name)
                                                .phone(phone)
                                                .build();

        String url = "http://localhost:" + port + "/api/v1/signup";

        //when
        ResponseEntity<Long> responseEntity = restTemplate.postForEntity(url, requestDto, Long.class);

        //then
        assertThat(responseEntity.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(responseEntity.getBody()).isGreaterThan(0L);

        List<User> all = userRepository.findAll();
        assertThat(all.get(0).getUsername()).isEqualTo(username);
//        assertThat(all.get(0).getPassword()).isEqualTo(passwordEncoder.encode(password));
        assertThat(all.get(0).getName()).isEqualTo(name);
        assertThat(all.get(0).getPhone()).isEqualTo(phone);
        assertThat(all.get(0).getAuthorities()).isEqualTo(Arrays.asList(new SimpleGrantedAuthority("USER")));

    }
}
