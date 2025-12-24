package com.techpost.appbatch.post.scraper;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.techpost.appbatch.post.enums.PublisherScrapEnum;
import com.techpost.domain.post.entity.Post;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

import java.io.IOException;
import java.util.List;

public abstract class PostScraper {

    protected PublisherScrapEnum publisherScrapEnum;
    protected ObjectMapper objectMapper;

    protected PostScraper(PublisherScrapEnum publisherScrapEnum, ObjectMapper objectMapper) {
        this.publisherScrapEnum = publisherScrapEnum;
        this.objectMapper = objectMapper;
    }

    public abstract List<Post> scrap();

    protected abstract List<Post> parsePosts(String jsonData) throws JsonProcessingException;

    protected String extractJsonData() throws IOException {
        return WebClient.builder()
                .baseUrl(publisherScrapEnum.getPostUrl()) // 기본 베이스 URL 설정
                .build()
                .get() // GET 요청 설정
                .retrieve() // HTTP 응답을 받아옴
                .onStatus(
                        status -> status.is4xxClientError() || status.is5xxServerError(), // 4xx 또는 5xx 에러 처리
                        clientResponse -> clientResponse.bodyToMono(String.class)
                                .flatMap(errorBody -> {
                                    // TODO: 에러처리 보완
                                    // 에러 메시지를 포함하여 예외를 발생
                                    return Mono.error(new RuntimeException("Naver API error: " + errorBody));
                                })
                )
                .bodyToMono(String.class)
                .block(); // 응답 바디를 String 타입으로 변환
    }
}
