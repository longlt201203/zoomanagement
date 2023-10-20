package com.swp.ZooManagement.utils.enums;

public enum AccountGenderEnum {
    FEMALE("female"),
    MALE("male");

    private final String value;

    public String getValue() {
        return value;
    }

    AccountGenderEnum(String value) {
        this.value = value;
    }
}
