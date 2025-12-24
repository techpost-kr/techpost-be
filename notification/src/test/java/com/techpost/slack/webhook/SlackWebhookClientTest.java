package com.techpost.slack.webhook;

import com.techpost.slack.webhook.dto.SlackWebhookRequest;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class SlackWebhookClientTest {

    @Disabled("구현 필요")
    @Test
    @DisplayName("슬랙 웹훅 전송 테스트")
    public void testCase1() {

        // given
        String url = "";

        SlackWebhookRequest data = SlackWebhookRequest.of("TEST");

        // when
        SlackWebhookClient.postMessage(url, data);

        // then

    }
}
