package com.swp.ZooManagement.utils.enums;

public enum FoodTypeEnum {
    FRUIT_AND_VEGETABLE("fruit_and_vegetable"),
    GRAIN_AND_CEREAL("grain_and_cereal"),
    PROTEIN("protein");

    private final String value;

    public String getValue() {
        return value;
    }

    FoodTypeEnum(String value) {
        this.value = value;
    }
}
