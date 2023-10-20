package com.swp.ZooManagement.errors;

import com.swp.ZooManagement.core.ErrorReport;

import java.util.List;

public class ValidationErrorReport extends ErrorReport<List<ValidationError>> {
    public ValidationErrorReport(List<ValidationError> data) {
        super("Validation error", data);
    }
}
