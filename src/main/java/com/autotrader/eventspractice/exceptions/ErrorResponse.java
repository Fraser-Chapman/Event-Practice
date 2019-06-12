package com.autotrader.eventspractice.exceptions;

public class ErrorResponse {
    private final String reason;
    private final String errorCode;

    public ErrorResponse(String reason, String errorCode) {
        this.reason = reason;
        this.errorCode = errorCode;
    }

    public String getReason() {
        return reason;
    }

    public String getErrorCode() {
        return errorCode;
    }
}
