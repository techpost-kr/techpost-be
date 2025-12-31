package com.techpost.domain.post.model;

import lombok.Getter;

@Getter
class PostId {
    private final Long id;

    PostId() {
        this.id = null;
    }

    PostId(Long postId ) {
        this.id = postId;
    }

    public static PostId of() {
        return new PostId();
    }

    public static PostId of(Long postId) {
        return new PostId(postId);
    }
}

