package com.swp.ZooManagement.utils.enums;

public enum AccountRoleEnum {
    ADMIN("admin"),
    STAFF("staff"),
    TRAINER("trainer");

    private final String value;

    public String getValue() {
        return value;
    }

    AccountRoleEnum(String value) {
        this.value = value;
    }
}
