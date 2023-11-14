package com.springboot.frspringboot.web.domain.posts;

import static org.assertj.core.api.Assertions.assertThat;

import com.springboot.frspringboot.domain.posts.Posts;
import com.springboot.frspringboot.domain.posts.PostsRepository;
import java.util.List;
import org.junit.After;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

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
    public void 게시글저장_불러오기() {
        //given
        String title = "테스트 게시글";
        String content = "테스트 본문";

        postsRepository.save(Posts.builder()
                .title(title)
                .content(content)
                .author("pjhwork97@gmail.com")
                .build());
        // 값이 있으면 update, 없으면 insert 해준다.

        //when
        List<Posts> postsList = postsRepository.findAll();
        // 모든 데이터를 조회해오는 메소드이다.

        //then
        Posts posts = postsList.get(0);
        assertThat(posts.getTitle()).isEqualTo(title);
        assertThat(posts.getContent()).isEqualTo(content);
    }
}
