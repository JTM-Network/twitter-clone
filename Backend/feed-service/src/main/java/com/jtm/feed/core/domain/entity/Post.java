package com.jtm.feed.core.domain.entity;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Data
@Document("posts")
@NoArgsConstructor
public class Post {

    @Id
    private UUID id;
    private UUID accountId;
    private String message;

    private List<UUID> likes;
    private List<UUID> retweets;
    private List<Comment> comments;

    public Post(UUID accountId, String message) {
        this(UUID.randomUUID(), accountId, message);
    }

    public Post(UUID id, UUID accountId, String message) {
        this.id = id;
        this.accountId = accountId;
        this.message = message;
        this.likes = new ArrayList<>();
        this.retweets = new ArrayList<>();
        this.comments = new ArrayList<>();
    }


}
