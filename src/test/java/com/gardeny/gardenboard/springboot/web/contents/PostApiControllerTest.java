package com.gardeny.gardenboard.springboot.web.contents;

import com.gardeny.gardenboard.springboot.domain.contents.Category;
import com.gardeny.gardenboard.springboot.domain.contents.Post;
import com.gardeny.gardenboard.springboot.domain.contents.PostRepository;
import com.gardeny.gardenboard.springboot.web.contents.dto.PostSaveRequestDto;
import com.gardeny.gardenboard.springboot.web.contents.dto.PostUpdateRequestDto;
import org.junit.After;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class PostApiControllerTest {
    // todo : token setup 하여 테스트

    @LocalServerPort
    private int port;

    @Autowired
    private TestRestTemplate restTemplate;

    @Autowired
    private PostRepository postRepository;

    @After
    public void tearDown() throws Exception {
        postRepository.deleteAll();
    }

    @Test
    public void Post_save() throws Exception {
        //given
        String title = "title";
        Category category = Category.NORMAL;
        String content = "content";
        PostSaveRequestDto requestDto = PostSaveRequestDto.builder()
                                                .title(title)
                                                .category(category)
                                                .content(content)
                                                .author("author")
                                                .build();

        String url = "http://localhost:" + port + "/api/v1/post";

        //when
        ResponseEntity<Long> responseEntity = restTemplate.postForEntity(url, requestDto, Long.class);

        //then
        assertThat(responseEntity.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(responseEntity.getBody()).isGreaterThan(0L);

        List<Post> all = postRepository.findAll();
        assertThat(all.get(0).getTitle()).isEqualTo(title);
        assertThat(all.get(0).getCategory()).isEqualTo(category);
        assertThat(all.get(0).getContent()).isEqualTo(content);
    }

    @Test
    public void Post_update() throws Exception {
        //given
        Post savedPosts = postRepository.save(Post.builder().title("title")
                .category(Category.NORMAL)
                .content("content")
                .author("author")
                .build());

        Long updateId = savedPosts.getId();
        String expectedTitle = "title2";
        String expectedContent = "content2";

        PostUpdateRequestDto requestDto = PostUpdateRequestDto.builder()
                .title(expectedTitle)
                .content(expectedContent)
                .build();

        String url = "http://localhost:" + port + "api/v1/post/" + updateId;

        HttpEntity<PostUpdateRequestDto> requestEntity = new HttpEntity<>(requestDto);

        //when
        ResponseEntity<Long> responseEntity = restTemplate.exchange(url, HttpMethod.PUT, requestEntity, Long.class);

        //then
        List<Post> all = postRepository.findAll();
        assertThat(all.get(0).getTitle()).isEqualTo(expectedTitle);
        assertThat(all.get(0).getContent()).isEqualTo(expectedContent);
    }
}
