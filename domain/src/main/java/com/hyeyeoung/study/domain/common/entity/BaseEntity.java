package com.hyeyeoung.study.domain.common.entity;

import jakarta.persistence.Column;
import jakarta.persistence.EntityListeners;
import jakarta.persistence.MappedSuperclass;
import lombok.Getter;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDateTime;

@Getter
@MappedSuperclass
@EntityListeners(AuditingEntityListener.class)
public abstract class BaseEntity {

    @CreatedBy
    @Column
    protected Long createdBy;

    @CreatedDate
    @Column
    protected LocalDateTime createdDateTime;

    @LastModifiedBy
    @Column
    protected Long modifiedBy;

    @LastModifiedDate
    @Column
    protected LocalDateTime modifiedDateTime;

}
