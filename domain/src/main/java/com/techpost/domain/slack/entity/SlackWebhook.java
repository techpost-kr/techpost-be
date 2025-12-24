package com.techpost.domain.slack.entity;


import com.techpost.domain.slack.enums.SlackWebhookEnum;
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
@Table(name = "SlackWebhooks")
public class SlackWebhook {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long slackWebhookSeq;

    @Column
    @Enumerated(EnumType.STRING)
    private SlackWebhookEnum slackWebhookEnum;

    @Column
    private String url;

    @Column
    private Long createdBy;

    @Column
    private LocalDateTime createdDate;

    @Column
    private Long modifiedBy;

    @Column
    private LocalDateTime modifiedDate;


}
