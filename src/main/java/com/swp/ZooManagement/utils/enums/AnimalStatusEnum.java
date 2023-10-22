package com.swp.ZooManagement.utils.enums;

public enum AnimalStatusEnum {
    HEALTHY("healthy"),
    SICK("sick"),
    IN_DANGER("inDanger"),
    DEAD("dead");

    private final String value;

    public String getValue() {
        return value;
    }

    AnimalStatusEnum(String value) {
        this.value = value;
    }
}
