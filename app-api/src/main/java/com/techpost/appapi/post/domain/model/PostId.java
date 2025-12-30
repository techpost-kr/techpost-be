package com.techpost.appapi.post.domain.model;

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
