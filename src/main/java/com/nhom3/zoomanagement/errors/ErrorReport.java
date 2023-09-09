package com.nhom3.zoomanagement.errors;

public class ErrorReport {
    private String message;

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public ErrorReport(String message) {
        this.message = message;
    }
}
