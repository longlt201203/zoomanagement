package com.swp.ZooManagement.utils.enums;

public enum SaleReportTypeEnum {
    WEEK("week"),
    MONTH("month"),
    YEAR("year");

    private final String value;

    public String getValue() {
        return value;
    }

    SaleReportTypeEnum(String value) {
        this.value = value;
    }
}
