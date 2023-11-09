package com.swp.ZooManagement.utils.enums;

public enum TicketStatusEnum {
    INACTIVE("inactive"),
    ACTIVE("active");

    private final String value;

    public String getValue() {
        return value;
    }

    TicketStatusEnum(String value) {
        this.value = value;
    }
}
