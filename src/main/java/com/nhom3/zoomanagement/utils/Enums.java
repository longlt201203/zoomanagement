package com.nhom3.zoomanagement.utils;

import lombok.AllArgsConstructor;
import lombok.Getter;

public class Enums {

    @Getter
    @AllArgsConstructor
    public enum HumanGenderEnum {
        MALE,
        FEMALE,
        OTHER
    }

    public enum RoleEnum {
        STAFF,

        ZOO_TRAINER,
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
}
