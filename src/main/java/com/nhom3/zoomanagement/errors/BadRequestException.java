package com.nhom3.zoomanagement.errors;

public class BadRequestException extends Throwable {
    private ErrorReport report;

    public ErrorReport getReport() {
        return report;
    }

    public void setReport(ErrorReport report) {
        this.report = report;
    }

    public BadRequestException(ErrorReport report) {
        this.report = report;
    }
}
