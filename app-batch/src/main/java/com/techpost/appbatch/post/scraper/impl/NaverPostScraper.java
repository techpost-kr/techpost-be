package com.techpost.appbatch.post.scraper.impl;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.techpost.appbatch.post.enums.PublisherScrapEnum;
import com.techpost.appbatch.post.json.NaverJsonData;
import com.techpost.appbatch.post.scraper.PostScraper;
import com.techpost.domain.post.entity.Post;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.List;

@Component
@Slf4j
public class NaverPostScraper extends PostScraper {

    public NaverPostScraper(ObjectMapper objectMapper) {
        super(PublisherScrapEnum.NAVER, objectMapper);
    }

    @Override
    public List<Post> scrap() {
        try {
            String jsonData = super.extractJsonData();
            return parsePosts(jsonData);
        } catch (IOException e) {
            // TODO: 오류를 보완하자
            throw new RuntimeException("Failed to extract JSON data from blog URL: " + publisherScrapEnum.getPostUrl(), e);
        }
    }

    @Override
    protected List<Post> parsePosts(String jsonData) throws JsonProcessingException {
        NaverJsonData naverJsonData = super.objectMapper.readValue(jsonData, NaverJsonData.class);
        return naverJsonData.toPosts();
    }
}
