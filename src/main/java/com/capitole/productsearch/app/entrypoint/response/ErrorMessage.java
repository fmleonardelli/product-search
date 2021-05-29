package com.capitole.productsearch.app.entrypoint.response;

import java.time.LocalDateTime;

public class ErrorMessage {

    private final LocalDateTime timestamp;
    private final String error;
    private final String message;

    public ErrorMessage(final LocalDateTime timestamp,
                        final String error,
                        final String message) {
        this.timestamp = timestamp;
        this.error = error;
        this.message = message;
    }

    public LocalDateTime getTimestamp() {
        return timestamp;
    }

    public String getError() {
        return error;
    }

    public String getMessage() {
        return message;
    }
}
