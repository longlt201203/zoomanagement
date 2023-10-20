package com.swp.ZooManagement.errors;

import com.swp.ZooManagement.core.ErrorReport;

import java.util.HashMap;
import java.util.Map;

public class EntityNotFoundErrorReport extends ErrorReport<Map<String, String>> {
    public EntityNotFoundErrorReport(String fieldName, String value) {
        super("Entity not found", new HashMap<>() {{
            put("field", fieldName);
            put("value", value);
        }});
    }
}
