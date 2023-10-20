package com.swp.ZooManagement.errors;

import com.swp.ZooManagement.core.ErrorReport;

import java.util.Map;

public class JwtErrorReport extends ErrorReport<Map<String, Object>> {
    public JwtErrorReport(String message, Map<String, Object> data) {
        super(message, data);
    }
}
