package com.godngu.webservice.domain.posts;

import org.junit.After;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.time.LocalDateTime;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringRunner.class)
@SpringBootTest
public class PostsRepositoryTest {

    @Autowired
    PostsRepository postsRepository;

    @After
    public void cleanup() {
        postsRepository.deleteAll();
    }

    @Test
    public void posts_save_find() {
        // given
        postsRepository.save(Posts.builder()
                                  .title("test title")
                                  .content("test content")
                                  .author("godngu@gmail.com")
                                  .build());

        // when
        List<Posts> postsList = postsRepository.findAll();

        // then
        Posts posts = postsList.get(0);
        assertThat(posts.getTitle()).isEqualTo("test title");
        assertThat(posts.getContent()).isEqualTo("test content");
    }

    @Test
    public void BaseTimeEntity_save() {
        // given
        LocalDateTime now = LocalDateTime.now();
        postsRepository.save(Posts.builder()
                                  .title("test title")
                                  .content("test content")
                                  .author("godngu@gmail.com")
                                  .build());

        // when
        List<Posts> postsList = postsRepository.findAll();

        // then
        Posts posts = postsList.get(0);
        assertThat(posts.getCreatedDate()).isGreaterThan(now);
        assertThat(posts.getModifiedDate()).isGreaterThan(now);
    }
}
