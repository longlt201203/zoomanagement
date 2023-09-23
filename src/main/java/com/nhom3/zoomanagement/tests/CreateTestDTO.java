package com.nhom3.zoomanagement.tests;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public class CreateTestDTO {
    @NotNull(message = "testNotNullField cannot be null")
    @NotBlank(message = "testNotNullField cannot be blank")
    private String testNotNullField;

    private String nullableField;

    private String uniqueField;

    public String getTestNotNullField() {
        return testNotNullField;
    }

    public void setTestNotNullField(String testNotNullField) {
        this.testNotNullField = testNotNullField;
    }

    public String getNullableField() {
        return nullableField;
    }

    public void setNullableField(String nullableField) {
        this.nullableField = nullableField;
    }

    public String getUniqueField() {
        return uniqueField;
    }

    public void setUniqueField(String uniqueField) {
        this.uniqueField = uniqueField;
    }
}
