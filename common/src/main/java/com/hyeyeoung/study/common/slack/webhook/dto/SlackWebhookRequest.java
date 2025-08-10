package com.hyeyeoung.study.common.slack.webhook.dto;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * <a href="https://api.slack.com/messaging/webhooks">...</a>
 */
@Getter
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class SlackWebhookRequest {

    private String text;

    public static SlackWebhookRequest of(String text) {
        return new SlackWebhookRequest(text);
    }
}
