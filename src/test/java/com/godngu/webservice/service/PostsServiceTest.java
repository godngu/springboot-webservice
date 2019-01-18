package com.godngu.webservice.service;

import com.godngu.webservice.domain.posts.Posts;
import com.godngu.webservice.domain.posts.PostsRepository;
import com.godngu.webservice.dto.PostsSaveRequestDto;
import org.junit.After;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringRunner.class)
@SpringBootTest
public class PostsServiceTest {

    @Autowired
    private PostsService postsService;

    @Autowired
    private PostsRepository postsRepository;

    @After
    public void cleanup() throws Exception {
        postsRepository.deleteAll();
    }

    @Test
    public void save() {
        final String title = "test title";
        final String content = "test content";
        final String author = "godngu@gmail.com";

        // given
        PostsSaveRequestDto dto =
            PostsSaveRequestDto.builder()
                               .title(title)
                               .content(content)
                               .author(author
                               ).build();

        // when
        postsService.save(dto);

        // then
        Posts posts = postsRepository.findAll().get(0);
        assertThat(posts.getTitle()).isEqualTo(title);
        assertThat(posts.getContent()).isEqualTo(content);
        assertThat(posts.getAuthor()).isEqualTo(author);
    }
}
