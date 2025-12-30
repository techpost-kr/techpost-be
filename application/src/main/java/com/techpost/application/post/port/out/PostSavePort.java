package com.techpost.application.post.port.out;

import com.techpost.domain.post.model.Post;

import java.util.List;

/**
 * 게시물 저장 포트 (아웃바운드)
 */
public interface PostSavePort {

    /**
     * 게시물 저장
     *
     * @param post 저장할 게시물
     * @return 저장된 게시물
     */
    Post save(Post post);

    /**
     * 게시물 일괄 저장
     *
     * @param posts 저장할 게시물 목록
     * @return 저장된 게시물 목록
     */
    List<Post> saveAll(List<Post> posts);
}

