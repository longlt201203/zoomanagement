package com.nhom3.zoomanagement.utils;

import lombok.AllArgsConstructor;
import lombok.Getter;

public class Enums {

    @Getter
    @AllArgsConstructor
    public enum AccountGenderEnum {
        MALE,
        FEMALE,
        OTHER
    }

    public enum AccountStatusEnum {
        ACTIVE,
        INACTIVE
    }

    public enum RoleEnum {
        STAFF,

        TRAINER,
        ADMIN
    }

    public enum TicketTypeEnum {
        ADULT,
        CHILDREN
    }

    public enum AnimalGenderEnum {
        FEMALE,
        MALE,
        HERMAPHRODITE,
        ASEXUAL
    }

    public enum AnimalStatusEnum {
        HEALTHY,
        SICK,
        IN_DANGER,
        DEAD
    }

    public enum MealStatusEnum {
        FED,
        NOT_FED_YET
    }

    public enum OrderStatusEnum {
        DONE,
        PENDING,
        CANCELLED
    }

    public enum SearchOperationEnum {

        CONTAINS, EQUAL, GREATER_THAN, GREATER_THAN_EQUAL, LESS_THAN, LESS_THAN_EQUAL;
        public static final String[] SIMPLE_OPERATION_SET = {"cn", "eq", "gt", "ge", "lt", "le"};

        public static SearchOperationEnum getSimpleOperation(
                final String input) {
            return switch (input) {
                case "cn" -> CONTAINS;
                case "eq" -> EQUAL;
                case "gt" -> GREATER_THAN;
                case "ge" -> GREATER_THAN_EQUAL;
                case "lt" -> LESS_THAN;
                case "le" -> LESS_THAN_EQUAL;
                default -> null;
            };
        }
    }
}
