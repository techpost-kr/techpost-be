package com.techpost.appbatch.domain.techblogscrap.job;

import com.techpost.domain.post.entity.Post;
import com.techpost.domain.post.repository.PostRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.batch.item.Chunk;
import org.springframework.batch.item.ItemWriter;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
@Slf4j
public class TechBlogScrapWriter implements ItemWriter<List<Post>> {

    private final PostRepository postRepository;

    @Override
    public void write(Chunk<? extends List<Post>> chunk) {

        log.info("start scrap reader");

        List<Post> posts = chunk.getItems().stream()
                .flatMap(List::stream)
                .collect(Collectors.toList());

        postRepository.saveAll(posts);

    }
}
