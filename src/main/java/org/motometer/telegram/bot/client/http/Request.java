package org.motometer.telegram.bot.client.http;

import org.immutables.value.Value;

import javax.annotation.Nullable;

@Value.Immutable
public interface Request {

    HttpMethod httpMethod();

    String url();

    @Nullable
    String body();

    enum HttpMethod {
        GET,
        POST
    }
}
