package com.techpost.domain.post.model;

import lombok.Getter;

@Getter
class PostId {
    private final Long id;

    PostId() {
        this.id = null;
    }

    public static PostId of() {
        return new PostId();
    }
}

