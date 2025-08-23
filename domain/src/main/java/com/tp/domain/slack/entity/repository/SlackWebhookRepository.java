package com.tp.domain.slack.entity.repository;

import com.tp.domain.slack.entity.SlackWebhook;
import com.tp.domain.slack.enums.SlackWebhookEnum;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface SlackWebhookRepository extends JpaRepository<SlackWebhook, Long> {

    Optional<SlackWebhook> findBySlackWebhookEnum(SlackWebhookEnum slackWebhookEnum);

}
