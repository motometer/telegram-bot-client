package org.motometer.telegram.bot.client;

import org.apache.commons.io.IOUtils;
import org.junit.jupiter.api.Test;
import org.motometer.telegram.bot.Action;
import org.motometer.telegram.bot.Bot;
import org.motometer.telegram.bot.api.ChatType;
import org.motometer.telegram.bot.api.Update;

import java.nio.charset.Charset;
import java.util.concurrent.CompletableFuture;

import static org.assertj.core.api.Assertions.assertThat;

class DefaultBotTest {

    private static final String UPDATE = "/org/motometer/telegram/api/UpdateTest/update.json";

    @Test
    void createWebHookListener() throws Exception {
        Bot bot = BotBuilder.defaultBuilder().token("token").build();

        CompletableFuture<Update> result = new CompletableFuture<>();

        bot.adaptListener(e -> {
            result.complete(e);
            return Action.empty();
        })
            .onEvent(IOUtils.resourceToString(UPDATE, Charset.defaultCharset()));

        assertThat(result.isDone());
        Update update = result.get();
        assertThat(update.message().chat().type()).isEqualTo(ChatType.PRIVATE_CHAT);
    }
}