package com.tp.appbatch.domain.techblogscrap.scraper.impl;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.tp.appbatch.domain.techblogscrap.enums.TechBlogScrapEnum;
import com.tp.appbatch.domain.techblogscrap.json.KakaoJsonData;
import com.tp.appbatch.domain.techblogscrap.scraper.TechBlogScraper;
import com.tp.domain.techblog.entity.TechBlogPost;
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
        KakaoJsonData kakaoJsonData = super.objectMapper.readValue(jsonData, KakaoJsonData.class);
        return kakaoJsonData.toTechBlogPosts();
    }
}
