package com.jtm.feed.core.usecase.repository;

import com.jtm.feed.core.domain.entity.Post;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Flux;

import java.util.UUID;

@Repository
public interface PostRepository extends ReactiveMongoRepository<Post, UUID> {

    Flux<Post> findByAccountId(UUID id);

}
