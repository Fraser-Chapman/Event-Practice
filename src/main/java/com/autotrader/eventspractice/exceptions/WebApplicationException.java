package com.autotrader.eventspractice.exceptions;

public class WebApplicationException extends RuntimeException {
    private final int statusCode;

    public WebApplicationException(int statusCode, String message) {
        super(message);
        this.statusCode = statusCode;
    }

    public WebApplicationException(Throwable cause, int statusCode, String message) {
        super(message, cause);
        this.statusCode = statusCode;
    }

    public int getStatusCode() {
        return statusCode;
    }
}
