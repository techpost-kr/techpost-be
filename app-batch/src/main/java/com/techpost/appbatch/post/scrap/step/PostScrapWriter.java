package com.techpost.appbatch.post.scrap.step;

import com.techpost.appbatch.post.scrap.dto.PostScrapDto;
import com.techpost.application.post.port.in.PostSaveUseCase;
import com.techpost.application.post.port.in.PostSaveCommand;
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
public class PostScrapWriter implements ItemWriter<List<PostScrapDto>> {

    private final PostSaveUseCase postSaveUseCase;

    @Override
    public void write(Chunk<? extends List<PostScrapDto>> chunk) {

        log.info("start scrap writer");

        List<PostSaveCommand> commands = chunk.getItems().stream()
                .flatMap(List::stream)
                .map(PostScrapDto::toCommand)
                .collect(Collectors.toList());

        // UseCase를 통해 게시물 저장
        postSaveUseCase.saveAll(commands);

        log.info("saved {} posts", commands.size());
    }


}
