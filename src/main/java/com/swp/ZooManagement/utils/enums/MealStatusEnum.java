package com.swp.ZooManagement.utils.enums;

public enum MealStatusEnum {
    NOT_FEED("not_feed"),
    FED("fed");

    private final String value;

    public String getValue() {
        return value;
    }

    MealStatusEnum(String value) {
        this.value = value;
    }
}
