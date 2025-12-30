package com.techpost.appbatch.post.scrap.json;

import com.techpost.appbatch.post.scrap.enums.PublisherScrapEnum;
import com.techpost.domain.post.model.Post;
import lombok.Getter;
import lombok.Setter;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.List;
import java.util.stream.Collectors;

@Getter
@Setter
public class NaverJsonData {
    private List<NaverContent> content;

    public List<Post> toPosts() {
        return content.stream().map(NaverContent::toPost)
                .collect(Collectors.toList());
    }

    @Getter
    @Setter
    public static class NaverContent {
        private String author;
        private String postHtml;
        private String postImage;
        private Long postPublishedAt;
        private String postTitle;
        private String url;

        private Post toPost() {
            return Post.of(PublisherScrapEnum.NAVER.getPublisher(),
                    this.postTitle,
                    PublisherScrapEnum.NAVER.getPostUrl(this.url),
                    toPublishedDateTime());
        }

        private LocalDateTime toPublishedDateTime() {
            return LocalDateTime.ofInstant(Instant.ofEpochMilli(this.postPublishedAt), ZoneId.systemDefault());
        }
    }

}
