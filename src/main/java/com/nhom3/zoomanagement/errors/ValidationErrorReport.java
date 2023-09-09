package com.nhom3.zoomanagement.errors;

import java.util.List;

public class ValidationErrorReport extends ErrorReport {
    private List<String> errMsgs;

    public List<String> getErrMsgs() {
        return errMsgs;
    }

    public void setErrMsgs(List<String> errMsgs) {
        this.errMsgs = errMsgs;
    }

    public ValidationErrorReport(List<String> errMsgs) {
        super("Validation failed");
        this.errMsgs = errMsgs;
    }
}
