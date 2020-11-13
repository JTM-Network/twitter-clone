package com.jtm.feed.presenter.controller;

import com.jtm.feed.core.domain.entity.Post;
import com.jtm.feed.data.service.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Mono;

import java.util.UUID;

@RestController
@RequestMapping("/post")
public class PostController {

    private final PostService postService;

    @Autowired
    public PostController(PostService postService) {
        this.postService = postService;
    }

    @PostMapping
    public Mono<Post> addPost(@RequestBody Post post) {
        return postService.insertPost(post);
    }

    @GetMapping("/{id}")
    public Mono<Post> getPost(@PathVariable UUID id) {
        return postService.getPost(id);
    }

    @DeleteMapping("/{id}")
    public Mono<Post> deletePost(@PathVariable UUID id) {
        return postService.removePost(id);
    }
}
