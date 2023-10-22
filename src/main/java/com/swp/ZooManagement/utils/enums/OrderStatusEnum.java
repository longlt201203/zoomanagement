package com.swp.ZooManagement.utils.enums;

public enum OrderStatusEnum {
    PENDING("pending"),
    DONE("done"),
    CANCELLED("cancelled");

    private final String value;

    public String getValue() {
        return value;
    }

    OrderStatusEnum(String value) {
        this.value = value;
    }
}
