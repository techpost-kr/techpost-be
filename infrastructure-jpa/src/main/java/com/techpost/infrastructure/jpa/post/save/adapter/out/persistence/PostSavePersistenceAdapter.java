package com.techpost.infrastructure.jpa.post.save.adapter.out.persistence;

import com.techpost.application.post.save.port.out.PostSavePort;
import com.techpost.domain.post.model.Post;
import com.techpost.infrastructure.jpa.post.common.entity.PostJpaEntity;
import com.techpost.infrastructure.jpa.post.common.mapper.PostEntityMapper;
import com.techpost.infrastructure.jpa.post.common.repository.PostJpaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

import static com.techpost.infrastructure.jpa.common.util.QuerydslUtils.eq;

/**
 * Post 영속성 어댑터
 * - LoadPostPort, SavePostPort 구현
 * - 도메인 포트(out)를 JPA로 구현
 */
@Component
@RequiredArgsConstructor
public class PostSavePersistenceAdapter implements PostSavePort {

    private final PostJpaRepository postJpaRepository;
    private final PostEntityMapper mapper;

    @Override
    public Post save(Post post) {
        PostJpaEntity entity = mapper.toEntity(post);
        PostJpaEntity saved = postJpaRepository.save(entity);
        return mapper.toDomain(saved);
    }

    @Override
    public List<Post> saveAll(List<Post> posts) {
        List<PostJpaEntity> entities = posts.stream()
                .map(mapper::toEntity)
                .collect(Collectors.toList());

        List<PostJpaEntity> saved = postJpaRepository.saveAll(entities);

        return saved.stream()
                .map(mapper::toDomain)
                .collect(Collectors.toList());
    }

}
