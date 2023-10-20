package com.swp.ZooManagement.utils.enums;

public enum AccountStatusEnum {
    INACTIVE("inactive"),
    ACTIVE("active");

    private final String value;

    public String getValue() {
        return value;
    }

    AccountStatusEnum(String value) {
        this.value = value;
    }
}
