package com.techpost.application.post.service;

import com.techpost.application.post.port.in.PostSaveUseCase;
import com.techpost.application.post.port.in.PostSaveCommand;
import com.techpost.application.post.port.out.PostSavePort;
import com.techpost.domain.post.model.Post;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

/**
 * 게시물 일괄 저장 서비스
 */
@Slf4j
@Service
@RequiredArgsConstructor
@Transactional
class PostSaveService implements PostSaveUseCase {

    private final PostSavePort postSavePort;

    @Override
    public void saveAll(List<PostSaveCommand> commands) {
        log.info("Saving {} posts", commands.size());

        List<Post> posts = commands.stream()
                .map(PostSaveCommand::toPost)
                .collect(Collectors.toList());

        postSavePort.saveAll(posts);
    }


}

