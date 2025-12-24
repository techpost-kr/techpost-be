package com.techpost.appbatch.post.job;

import com.techpost.domain.post.entity.Post;
import com.techpost.domain.post.repository.PostRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.batch.infrastructure.item.Chunk;
import org.springframework.batch.infrastructure.item.ItemWriter;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
@Slf4j
public class PostScrapWriter implements ItemWriter<List<Post>> {

    private final PostRepository postRepository;

    @Override
    public void write(Chunk<? extends List<Post>> chunk) {

        log.info("start scrap writer");

        List<Post> posts = chunk.getItems().stream()
                .flatMap(List::stream)
                .collect(Collectors.toList());

        postRepository.saveAll(posts);

    }
}
