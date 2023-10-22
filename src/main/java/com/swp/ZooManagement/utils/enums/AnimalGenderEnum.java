package com.swp.ZooManagement.utils.enums;

public enum AnimalGenderEnum {
    FEMALE("female"),
    MALE("male"),
    HERMAPHRODITE("hermaphrodite"),
    ASEXUAL("asexual");

    private final String value;

    public String getValue() {
        return value;
    }

    AnimalGenderEnum(String value) {
        this.value = value;
    }
}
