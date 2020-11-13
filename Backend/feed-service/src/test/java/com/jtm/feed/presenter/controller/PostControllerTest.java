package com.jtm.feed.presenter.controller;

import com.jtm.feed.core.domain.entity.Post;
import com.jtm.feed.data.service.PostService;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.reactive.AutoConfigureWebTestClient;
import org.springframework.boot.test.autoconfigure.web.reactive.WebFluxTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.reactive.server.WebTestClient;
import reactor.core.publisher.Mono;

import java.util.UUID;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@RunWith(SpringRunner.class)
@WebFluxTest(PostController.class)
@AutoConfigureWebTestClient
public class PostControllerTest {

    @MockBean
    private PostService postService;

    @Autowired
    private WebTestClient webTestClient;

    private UUID accountId;
    private Post created;

    @Before
    public void setUp() {
        accountId = UUID.randomUUID();
        created = new Post(accountId, "Posting message...");
    }

    @Test
    public void addPostTest() {
        when(postService.insertPost(any(Post.class))).thenReturn(Mono.just(created));

        webTestClient.post()
                .uri("/post")
                .bodyValue(new Post())
                .exchange()
                .expectStatus().isOk()
                .expectBody()
                .jsonPath("$.accountId").isEqualTo(accountId.toString())
                .jsonPath("$.message").isEqualTo("Posting message...");

        verify(postService, times(1)).insertPost(any(Post.class));
        verifyNoMoreInteractions(postService);
    }

    @Test
    public void getPostTest() {
        when(postService.getPost(any(UUID.class))).thenReturn(Mono.just(created));

        webTestClient.get()
                .uri("/post/" + UUID.randomUUID().toString())
                .exchange()
                .expectStatus().isOk()
                .expectBody()
                .jsonPath("$.accountId").isEqualTo(accountId.toString())
                .jsonPath("$.message").isEqualTo("Posting message...");

        verify(postService, times(1)).getPost(any(UUID.class));
        verifyNoMoreInteractions(postService);
    }

    @Test
    public void deletePostTest() {
        when(postService.removePost(any(UUID.class))).thenReturn(Mono.just(created));

        webTestClient.delete()
                .uri("/post/" + UUID.randomUUID().toString())
                .exchange()
                .expectStatus().isOk()
                .expectBody()
                .jsonPath("$.accountId").isEqualTo(accountId.toString())
                .jsonPath("$.message").isEqualTo("Posting message...");

        verify(postService, times(1)).removePost(any(UUID.class));
        verifyNoMoreInteractions(postService);
    }
}
