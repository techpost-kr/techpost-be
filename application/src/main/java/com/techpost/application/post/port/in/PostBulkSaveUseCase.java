package com.techpost.application.post.port.in;

import com.techpost.domain.post.model.Post;

import java.util.List;

/**
 * 게시물 일괄 저장 유스케이스 (인바운드 포트)
 */
public interface PostBulkSaveUseCase {

    /**
     * 게시물 일괄 저장
     *
     * @param posts 저장할 게시물 목록
     * @return 저장된 게시물 목록
     */
    List<Post> saveAll(List<Post> posts);
}
