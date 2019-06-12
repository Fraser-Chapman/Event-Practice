package com.autotrader.eventspractice.exceptions;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import javax.servlet.http.HttpServletRequest;

import static org.eclipse.jetty.http.HttpStatus.isClientError;
import static org.eclipse.jetty.http.HttpStatus.isServerError;

@ControllerAdvice
public class WebApplicationExceptionHandler extends ResponseEntityExceptionHandler {

    private static final Logger LOG = LoggerFactory.getLogger(WebApplicationExceptionHandler.class);
    private static final String SERVER_ERROR = "Server Error %s:";
    private static final String CLIENT_ERROR = "Client Error %s %s";

    @ExceptionHandler(value = WebApplicationException.class)
    protected ResponseEntity toResponse(WebApplicationException ex, HttpServletRequest request) {
        int status = ex.getStatusCode();
        logExceptionIfApplicable(ex, status);
        ErrorResponse error = new ErrorResponse(ex.getMessage(), String.valueOf(status));
        return ResponseEntity.status(status).body(error);
    }

    private void logExceptionIfApplicable(WebApplicationException e, int status) {
        if (isServerError(status)) {
            LOG.error(String.format(SERVER_ERROR, status), e);
        }
        else if (isClientError(status)) {
            LOG.error(String.format(CLIENT_ERROR, status, e.getMessage()));
        }
    }
}
