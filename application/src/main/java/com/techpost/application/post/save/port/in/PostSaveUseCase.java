package com.techpost.application.post.save.port.in;

import java.util.List;

/**
 * 게시물 일괄 저장 유스케이스 (인바운드 포트)
 */
public interface PostSaveUseCase {

    /**
     * 게시물 일괄 저장
     *
     * @param commands 저장할 게시물 커맨드 목록
     */
    void saveAll(List<PostSaveCommand> commands);
}
