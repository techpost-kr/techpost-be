package com.techpost.appbatch.post.scrap.enums;

import com.techpost.appbatch.post.scrap.scraper.PostScraper;
import com.techpost.appbatch.post.scrap.scraper.impl.KakaoPostScraper;
import com.techpost.appbatch.post.scrap.scraper.impl.NaverPostScraper;
import com.techpost.domain.post.model.Publisher;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.Arrays;

@Getter
@AllArgsConstructor
public enum PublisherScrapEnum {

    KAKAO(Publisher.KAKAO, "https://tech.kakao.com/api/v1/posts/no-offset?categoryCode=blog&lastSeq=0&firstSeq=0", "https://tech.kakao.com/posts/%s", KakaoPostScraper.class),
    NAVER(Publisher.NAVER, "https://d2.naver.com/api/v1/contents?categoryId=2&page=0&size=10", "https://d2.naver.com%s", NaverPostScraper.class),
    ;

    private final Publisher publisher;
    private final String postUrl;
    private final String postUrlFormat;
    private final Class<? extends PostScraper> postScraperClass;

    public String getPostUrl(String postNumber) {
        return String.format(this.postUrlFormat, postNumber);
    }

    public static PublisherScrapEnum findByPostScraperClass(Class<? extends PostScraper> postScraperClass) {
        return Arrays.stream(PublisherScrapEnum.values())
                .filter(scrapEnum -> scrapEnum.getPostScraperClass().equals(postScraperClass))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("No matching PublisherScrapEnum found for class: " + postScraperClass.getName()));
    }
}
