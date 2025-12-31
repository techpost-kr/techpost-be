package com.techpost.application.post.save.port.in;

import com.techpost.domain.post.model.Post;
import com.techpost.domain.post.model.Publisher;

import java.time.LocalDateTime;

/**
 * 게시물 저장 커맨드
 */
public record PostSaveCommand(
        Publisher publisher,
        String title,
        String url,
        LocalDateTime publishedAt
) {
    public PostSaveCommand {
        if (publisher == null) {
            throw new IllegalArgumentException("Publisher cannot be null");
        }
        if (title == null || title.isBlank()) {
            throw new IllegalArgumentException("Title cannot be null or blank");
        }
        if (url == null || url.isBlank()) {
            throw new IllegalArgumentException("URL cannot be null or blank");
        }
        if (publishedAt == null) {
            throw new IllegalArgumentException("PublishedAt cannot be null");
        }
    }

    public static Post toPost(PostSaveCommand command) {
        return Post.of(
                command.publisher(),
                command.title(),
                command.url(),
                command.publishedAt()
        );
    }
}

