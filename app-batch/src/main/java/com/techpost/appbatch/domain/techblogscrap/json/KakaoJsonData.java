package com.techpost.appbatch.domain.techblogscrap.json;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.techpost.appbatch.domain.techblogscrap.enums.TechBlogScrapEnum;
import com.techpost.domain.post.entity.Post;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Getter
@Setter
public class KakaoJsonData {
    private List<KakaoPage> pages;

    public List<Post> toPosts() {
        return pages.stream()
                .flatMap(page -> page.getContents().stream())
                .map(KakaoPage.KakaoContent::toPost)
                .collect(Collectors.toList());
    }


    @Getter
    @Setter
    private static class KakaoPage {
        private List<KakaoContent> contents;

        @Getter
        @Setter
        private static class KakaoContent {
            private int id;
            private String title;
            private String releaseDate;
            @JsonFormat(pattern = "yyyy.MM.dd HH:mm:ss")
            private LocalDateTime releaseDateTime; // LocalDateTime으로 변경
            private List<KakaoCategory> categories;
            private KakaoAuthor author;
            private String thumbnailUri;

            private Post toPost() {
                return Post.of(TechBlogScrapEnum.KAKAO.getPublisher(),
                        this.title,
                        TechBlogScrapEnum.KAKAO.getPostUrl(String.valueOf(this.id)),
                        this.releaseDateTime);
            }

            @Getter
            @Setter
            private static class KakaoAuthor {
                private String name;
                private String description;
                private String profile;
            }

            @Getter
            @Setter
            private static class KakaoCategory {
                private String code;
                private String name;
            }


        }
    }
}
