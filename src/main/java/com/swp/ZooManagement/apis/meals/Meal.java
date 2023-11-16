package com.swp.ZooManagement.apis.meals;

import com.swp.ZooManagement.apis.animals.Animal;
import com.swp.ZooManagement.apis.foods.Food;
import com.swp.ZooManagement.core.ResponsableEntity;
import jakarta.persistence.*;
import lombok.Data;

import java.time.Instant;
import java.util.ArrayList;
import java.util.List;

@Entity
@Data
public class Meal implements ResponsableEntity<MealResponseDto> {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne(optional = false)
    private Animal animal;

    @Column(nullable = false)
    private Instant time;

    @OneToMany(mappedBy = "meal", cascade = CascadeType.ALL, fetch = FetchType.EAGER, orphanRemoval = true)
    private List<MealDetail> details;

    @Override
    public MealResponseDto toResponseDto() {
        MealResponseDto responseDto = new MealResponseDto();
        responseDto.setId(id);
        responseDto.setTime(time);
        List<MealDetailResponseDto> detailResponseDtoList = new ArrayList<>();
        for (MealDetail detail : details) {
            MealDetailResponseDto detailResponseDto = new MealDetailResponseDto();
            detailResponseDto.setId(detail.getId());
            detailResponseDto.setFood(detail.getFood().toResponseDto());
            detailResponseDto.setAmount(detail.getAmount());
            detailResponseDtoList.add(detailResponseDto);
        }
        responseDto.setDetails(detailResponseDtoList);
        return responseDto;
    }
}
