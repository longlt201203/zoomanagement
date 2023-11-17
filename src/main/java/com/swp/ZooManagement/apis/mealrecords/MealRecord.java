package com.swp.ZooManagement.apis.mealrecords;

import com.swp.ZooManagement.apis.accounts.Account;
import com.swp.ZooManagement.apis.meals.Meal;
import com.swp.ZooManagement.core.ResponsableEntity;
import com.swp.ZooManagement.utils.enums.MealStatusEnum;
import jakarta.persistence.*;
import lombok.Data;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.Instant;

@Entity
@EntityListeners(AuditingEntityListener.class)
@Data
public class MealRecord implements ResponsableEntity<MealRecordResponseDto> {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne(optional = false)
    private Meal meal;

    @CreatedDate
    private Instant createdAt;

    @LastModifiedDate
    private Instant updatedAt;

//    @LastModifiedBy
//    private Account updatedBy;

    @Column(nullable = false)
    private MealStatusEnum status;

    @Override
    public MealRecordResponseDto toResponseDto() {
        MealRecordResponseDto responseDto = new MealRecordResponseDto();
        responseDto.setId(id);
        responseDto.setMeal(meal.toResponseDto());
        responseDto.setStatus(status);
        responseDto.setCreatedAt(createdAt);
        responseDto.setUpdatedAt(updatedAt);
//        responseDto.setUpdatedBy(updatedBy.toCreatorDto());
        return responseDto;
    }
}
