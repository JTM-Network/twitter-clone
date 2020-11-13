package com.jtm.feed.data.service;

import com.jtm.feed.core.domain.entity.Post;
import com.jtm.feed.core.usecase.repository.PostRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

import java.util.UUID;

@Service
public class PostService {

    private final PostRepository postRepository;

    @Autowired
    public PostService(PostRepository postRepository) {
        this.postRepository = postRepository;
    }

    public Mono<Post> insertPost(Post post) {
        return postRepository.insert(post);
    }

    public Mono<Post> getPost(UUID id) {
        return postRepository.findById(id);
    }

    public Mono<Post> removePost(UUID id) {
        return postRepository.findById(id)
                .flatMap(post -> postRepository.delete(post).thenReturn(post));
    }
}
