package com.swp.ZooManagement.utils.enums;

public enum NewsStatusEnum {
    HIDDEN("hidden"),
    PUBLISHED("published");

    private final String value;

    public String getValue() {
        return value;
    }

    NewsStatusEnum(String value) {
        this.value = value;
    }
}
