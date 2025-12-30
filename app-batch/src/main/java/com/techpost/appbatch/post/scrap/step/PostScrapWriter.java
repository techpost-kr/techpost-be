package com.techpost.appbatch.post.scrap.step;

import com.techpost.application.post.port.in.PostBulkSaveUseCase;
import com.techpost.domain.post.model.Post;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.batch.infrastructure.item.Chunk;
import org.springframework.batch.infrastructure.item.ItemWriter;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

/**
 * Post Scrap Writer
 * - Domain의 UseCase를 사용하여 게시물 저장
 */
@Component
@RequiredArgsConstructor
@Slf4j
public class PostScrapWriter implements ItemWriter<List<Post>> {

    private final PostBulkSaveUseCase postBulkSaveUseCase;

    @Override
    public void write(Chunk<? extends List<Post>> chunk) {

        log.info("start scrap writer");

        List<Post> posts = chunk.getItems().stream()
                .flatMap(List::stream)
                .collect(Collectors.toList());

        // UseCase를 통해 게시물 저장
        postBulkSaveUseCase.saveAll(posts);

        log.info("saved {} posts", posts.size());
    }
}
