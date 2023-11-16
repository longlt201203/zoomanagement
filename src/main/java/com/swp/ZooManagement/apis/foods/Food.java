package com.swp.ZooManagement.apis.foods;

import com.swp.ZooManagement.core.ResponsableEntity;
import com.swp.ZooManagement.utils.enums.FoodTypeEnum;
import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
public class Food implements ResponsableEntity<FoodResponseDto> {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(nullable = false)
    private FoodTypeEnum type;

    @Column(nullable = false, columnDefinition = "NVARCHAR(60)")
    private String name;

    @Column(nullable = false, columnDefinition = "NVARCHAR(30)")
    private String unit;

    @Column(nullable = false, columnDefinition = "NVARCHAR(255)")
    private String description;

    @Override
    public FoodResponseDto toResponseDto() {
        FoodResponseDto responseDto = new FoodResponseDto();
        responseDto.setId(id);
        responseDto.setType(type);
        responseDto.setName(name);
        responseDto.setUnit(unit);
        responseDto.setDescription(description);
        return responseDto;
    }
}
