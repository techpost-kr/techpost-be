package com.techpost.appbatch.domain.techblogscrap.scraper;

import com.techpost.appbatch.domain.techblogscrap.enums.PublisherScrapEnum;
import org.springframework.stereotype.Component;

import java.util.EnumMap;
import java.util.List;

@Component
public class TechBlogScraperFactory {

    private final EnumMap<PublisherScrapEnum, TechBlogScraper> scraperMap;

    public TechBlogScraperFactory(List<TechBlogScraper> scrapers) {
        scraperMap = createScraperMap(scrapers);
    }

    private EnumMap<PublisherScrapEnum, TechBlogScraper> createScraperMap(List<TechBlogScraper> scrapers) {
        EnumMap<PublisherScrapEnum, TechBlogScraper> scraperMap = new EnumMap<>(PublisherScrapEnum.class);

        // 각 스크래퍼를 순회하며 EnumMap에 추가
        for (TechBlogScraper scraper : scrapers) {
            PublisherScrapEnum scrapEnum = PublisherScrapEnum.findByTechBlogScraperClass(scraper.getClass());
            scraperMap.put(scrapEnum, scraper);
        }
        return scraperMap;
    }

    public TechBlogScraper getTechBlogScraper(PublisherScrapEnum publisherScrapEnum) {
        TechBlogScraper scraper = scraperMap.get(publisherScrapEnum);
        if (scraper == null) {
            throw new IllegalArgumentException("No scraper found for: " + publisherScrapEnum.getPublisher().getName());
        }
        return scraper;
    }
}
