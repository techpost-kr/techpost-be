package com.techpost.appbatch.post.scraper;

import com.techpost.appbatch.post.enums.PublisherScrapEnum;
import org.springframework.stereotype.Component;

import java.util.EnumMap;
import java.util.List;

@Component
public class PostScraperFactory {

    private final EnumMap<PublisherScrapEnum, PostScraper> scraperMap;

    public PostScraperFactory(List<PostScraper> scrapers) {
        scraperMap = createScraperMap(scrapers);
    }

    private EnumMap<PublisherScrapEnum, PostScraper> createScraperMap(List<PostScraper> scrapers) {
        EnumMap<PublisherScrapEnum, PostScraper> scraperMap = new EnumMap<>(PublisherScrapEnum.class);

        // 각 스크래퍼를 순회하며 EnumMap에 추가
        for (PostScraper scraper : scrapers) {
            PublisherScrapEnum scrapEnum = PublisherScrapEnum.findByPostScraperClass(scraper.getClass());
            scraperMap.put(scrapEnum, scraper);
        }
        return scraperMap;
    }

    public PostScraper getPostScraper(PublisherScrapEnum publisherScrapEnum) {
        PostScraper scraper = scraperMap.get(publisherScrapEnum);
        if (scraper == null) {
            throw new IllegalArgumentException("No scraper found for: " + publisherScrapEnum.getPublisher().getName());
        }
        return scraper;
    }
}
