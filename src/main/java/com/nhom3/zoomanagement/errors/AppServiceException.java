package com.nhom3.zoomanagement.errors;

public class AppServiceException extends BadRequestException {
    public AppServiceException(ErrorReport report) {
        super(report);
    }
}
