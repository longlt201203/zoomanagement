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
    
    public enum OrderStatus {
        DONE,
        PENDING,
        CANCELLED
    }
}
