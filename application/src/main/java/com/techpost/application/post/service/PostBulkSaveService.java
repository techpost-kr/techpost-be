package com.techpost.application.post.service;

import com.techpost.application.post.port.in.PostBulkSaveUseCase;
import com.techpost.application.post.port.out.PostSavePort;
import com.techpost.domain.post.model.Post;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * 게시물 일괄 저장 서비스
 */
@Slf4j
@Service
@RequiredArgsConstructor
@Transactional
class PostBulkSaveService implements PostBulkSaveUseCase {

    private final PostSavePort postSavePort;

    @Override
    public List<Post> saveAll(List<Post> posts) {
        log.info("Saving {} posts", posts.size());
        return postSavePort.saveAll(posts);
    }
}

