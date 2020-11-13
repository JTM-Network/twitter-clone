package com.jtm.feed.core.domain.entity;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.UUID;

@Data
@NoArgsConstructor
public class Comment {

    private UUID accountId;
    private List<Comment> comments;
}
