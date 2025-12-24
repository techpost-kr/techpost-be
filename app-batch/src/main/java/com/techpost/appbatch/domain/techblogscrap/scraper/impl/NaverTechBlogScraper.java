package com.techpost.appbatch.domain.techblogscrap.scraper.impl;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.techpost.appbatch.domain.techblogscrap.enums.TechBlogScrapEnum;
import com.techpost.appbatch.domain.techblogscrap.json.NaverJsonData;
import com.techpost.appbatch.domain.techblogscrap.scraper.TechBlogScraper;
import com.techpost.domain.techblog.entity.TechBlogPost;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.List;

@Component
@Slf4j
public class NaverTechBlogScraper extends TechBlogScraper {

    public NaverTechBlogScraper(ObjectMapper objectMapper) {
        super(TechBlogScrapEnum.NAVER, objectMapper);
    }

    @Override
    public List<TechBlogPost> scrap() {
        try {
            String jsonData = super.extractJsonData();
            return parseTechBlogPosts(jsonData);
        } catch (IOException e) {
            // TODO: 오류를 보완하자
            throw new RuntimeException("Failed to extract JSON data from blog URL: " + techBlogScrapEnum.getBlogUrl(), e);
        }
    }

    @Override
    protected List<TechBlogPost> parseTechBlogPosts(String jsonData) throws JsonProcessingException {
        NaverJsonData naverJsonData = super.objectMapper.readValue(jsonData, NaverJsonData.class);
        return naverJsonData.toTechBlogPosts();
    }
}
