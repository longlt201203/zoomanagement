package com.swp.ZooManagement.apis.cagemeals;

import com.swp.ZooManagement.apis.accounts.Account;
import com.swp.ZooManagement.apis.cages.Cage;
import com.swp.ZooManagement.apis.mealrecords.MealRecord;
import com.swp.ZooManagement.apis.mealrecords.MealRecordResponseDto;
import com.swp.ZooManagement.core.ResponsableEntity;
import jakarta.persistence.*;
import lombok.Data;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.Instant;
import java.util.ArrayList;
import java.util.List;

@Entity
@EntityListeners(AuditingEntityListener.class)
@Data
public class CageMeal implements ResponsableEntity<CageMealResponseDto> {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(nullable = false)
    private Instant time;

    @Column(nullable = false, columnDefinition = "NVARCHAR(100)")
    private String food;

    @ManyToOne
    @CreatedBy
    private Account createdBy;

    @ManyToOne(optional = false)
    private Cage cage;

    @OneToMany(mappedBy = "cageMeal")
    private List<MealRecord> meals;

    @Override
    public CageMealResponseDto toResponseDto() {
        CageMealResponseDto responseDto = new CageMealResponseDto();
        responseDto.setId(id);
        responseDto.setTime(time);
        responseDto.setFood(food);
        if (createdBy != null) {
            responseDto.setCreatedBy(createdBy.toCreatorDto());
        }
        List<MealRecordResponseDto> mealRecordResponseDtoList = new ArrayList<>();
        if (meals != null) {
            for (MealRecord meal : meals) {
                mealRecordResponseDtoList.add(meal.toResponseDto());
            }
        }
        responseDto.setMeals(mealRecordResponseDtoList);
        return responseDto;
    }
}
