package com.hyeyeoung.study.domain.techblog.entity;


import com.hyeyeoung.study.domain.common.constant.TableConstants;
import com.hyeyeoung.study.domain.common.entity.BaseEntity;
import com.hyeyeoung.study.domain.techblog.enums.TechBlogEnum;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Entity
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Table(name = TableConstants.TECH_BLOG_POST)
public class TechBlogPost extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long techBlogPostSeq;

    @Column
    @Enumerated(EnumType.STRING)
    private TechBlogEnum techBlogEnum;

    @Column
    private String title;

    @Column
    private String url;

    @Column
    private LocalDateTime publishedDateTime;

    public static TechBlogPost of(TechBlogEnum techBlogEnum,
                                  String title,
                                  String url,
                                  LocalDateTime publishedDateTime) {
        return TechBlogPost.builder()
                .techBlogEnum(techBlogEnum)
                .title(title)
                .url(url)
                .publishedDateTime(publishedDateTime)
                .build();
    }
}
