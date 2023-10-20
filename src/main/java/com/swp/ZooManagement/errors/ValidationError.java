package com.swp.ZooManagement.errors;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class ValidationError {
    private String field;
    private Object value;
    private String message;
}
