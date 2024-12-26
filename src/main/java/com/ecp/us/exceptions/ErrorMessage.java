package com.ecp.us.exceptions;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;


public class ErrorMessage {

    private String message;
    private int statusCode;
    private LocalDateTime timestamp;
    private String details;

    public ErrorMessage(String message, int statusCode, String details) {
        this.message = message;
        this.statusCode = statusCode;
        this.timestamp = LocalDateTime.now();
        this.details = details;
    }

    // Getters and Setters
    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public int getStatusCode() {
        return statusCode;
    }

    public void setStatusCode(int statusCode) {
        this.statusCode = statusCode;
    }

    public LocalDateTime getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(LocalDateTime timestamp) {
        this.timestamp = timestamp;
    }

    public String getDetails() {
        return details;
    }

    public void setDetails(String details) {
        this.details = details;
    }
}
