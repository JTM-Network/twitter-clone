package com.jtm.feed.data.service;

import com.jtm.feed.core.domain.entity.Post;
import com.jtm.feed.core.usecase.repository.PostRepository;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.junit4.SpringRunner;
import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;

import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@RunWith(SpringRunner.class)
public class PostServiceTest {

    private PostRepository postRepository;
    private PostService postService;

    private UUID accountId;
    private Post created;

    @Before
    public void setUp() {
        postRepository = mock(PostRepository.class);
        postService = new PostService(postRepository);
        accountId = UUID.randomUUID();
        created = new Post(accountId, "Posting message...");
    }

    @Test
    public void insertPostTest() {
        when(postRepository.insert(any(Post.class))).thenReturn(Mono.just(created));

        Mono<Post> inserted = postService.insertPost(new Post());

        verify(postRepository, times(1)).insert(any(Post.class));
        verifyNoMoreInteractions(postRepository);

        StepVerifier.create(inserted)
                .assertNext(post -> {
                    assertThat(post.getAccountId()).isEqualTo(accountId);
                    assertThat(post.getMessage()).isEqualTo("Posting message...");
                })
                .verifyComplete();
    }

    @Test
    public void getPostTest() {
        when(postRepository.findById(any(UUID.class))).thenReturn(Mono.just(created));

        Mono<Post> fetched = postService.getPost(UUID.randomUUID());

        verify(postRepository, times(1)).findById(any(UUID.class));
        verifyNoMoreInteractions(postRepository);

        StepVerifier.create(fetched)
                .assertNext(post -> {
                    assertThat(post.getAccountId()).isEqualTo(accountId);
                    assertThat(post.getMessage()).isEqualTo("Posting message...");
                })
                .verifyComplete();
    }

    @Test
    public void deletePostTest() {
        when(postRepository.findById(any(UUID.class))).thenReturn(Mono.just(created));

        Mono<Post> deleted = postService.removePost(UUID.randomUUID());

        verify(postRepository, times(1)).findById(any(UUID.class));
        verifyNoMoreInteractions(postRepository);
    }
}
