package com.techpost.appbatch.domain.techblogscrap.scraper.impl;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.techpost.appbatch.domain.techblogscrap.enums.TechBlogScrapEnum;
import com.techpost.appbatch.domain.techblogscrap.json.KakaoJsonData;
import com.techpost.appbatch.domain.techblogscrap.scraper.TechBlogScraper;
import com.techpost.domain.post.entity.Post;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.List;

@Component
@Slf4j
public class KakaoTechBlogScraper extends TechBlogScraper {

    public KakaoTechBlogScraper(ObjectMapper objectMapper) {
        super(TechBlogScrapEnum.KAKAO, objectMapper);
    }

    @Override
    public List<Post> scrap() {
        try {
            String jsonData = super.extractJsonData();
            return parsePosts(jsonData);
        } catch (IOException e) {
            // TODO: 오류를 보완하자
            throw new RuntimeException("Failed to extract JSON data from blog URL: " + techBlogScrapEnum.getBlogUrl(), e);
        }
    }

    @Override
    protected List<Post> parsePosts(String jsonData) throws JsonProcessingException {
        KakaoJsonData kakaoJsonData = super.objectMapper.readValue(jsonData, KakaoJsonData.class);
        return kakaoJsonData.toPosts();
    }
}
