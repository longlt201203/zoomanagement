package com.swp.ZooManagement.apis.animals;

import com.swp.ZooManagement.apis.accounts.Account;
import com.swp.ZooManagement.apis.animalspecies.AnimalSpecies;
import com.swp.ZooManagement.apis.cages.Cage;
import com.swp.ZooManagement.apis.meals.Meal;
import com.swp.ZooManagement.apis.meals.MealResponseDto;
import com.swp.ZooManagement.core.ResponsableEntity;
import com.swp.ZooManagement.utils.enums.AnimalGenderEnum;
import com.swp.ZooManagement.utils.enums.AnimalStatusEnum;
import jakarta.persistence.*;
import lombok.Data;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.Instant;
import java.util.ArrayList;
import java.util.List;

@Entity
@EntityListeners(AuditingEntityListener.class)
@Data
public class Animal implements ResponsableEntity<AnimalResponseDto> {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(columnDefinition = "NVARCHAR(100)")
    private String name;

    @Column(columnDefinition = "NVARCHAR(100)")
    private String nation;

    @Column
    private Instant dob;

    @Column
    private AnimalGenderEnum gender;

    @Column
    private AnimalStatusEnum status;

    @Column(nullable = false, columnDefinition = "NVARCHAR(1000)")
    private String description;

    @Column(columnDefinition = "NVARCHAR(1000)")
    private String note;

    @Column(columnDefinition = "TEXT")
    private String imageList;

    @ManyToOne(optional = false)
    private AnimalSpecies species;

    @Column(nullable = false)
    private Double weight;

    @Column(nullable = false)
    private Double height;

    @Column(nullable = false)
    private Double length;

    @Column(columnDefinition = "NVARCHAR(1000)")
    private String feedingGuide;

    @ManyToOne
    private Cage cage;

    @ManyToOne
    @CreatedBy
    private Account createdBy;

    @Column
    @CreatedDate
    private Instant createdAt;

    @ManyToOne
    @LastModifiedBy
    private Account updatedBy;

    @Column
    @LastModifiedDate
    private Instant updatedAt;

    @OneToMany(mappedBy = "animal")
    private List<Meal> meals;

    @Override
    public AnimalResponseDto toResponseDto() {
        AnimalResponseDto responseDto = new AnimalResponseDto();
        responseDto.setId(id);
        responseDto.setName(name);
        responseDto.setNation(nation);
        responseDto.setDob(dob);
        responseDto.setGender(gender);
        responseDto.setStatus(status);
        responseDto.setDescription(description);
        responseDto.setNote(note);
        responseDto.setImageList(imageList.isEmpty() ? List.of() : List.of(imageList.split(";")));
        responseDto.setSpecies(species.toResponseDto());
        if (cage != null) {
            responseDto.setCage(cage.toResponseDto());
        }
        responseDto.setCreatedAt(createdAt);
        responseDto.setUpdatedAt(updatedAt);
        if (createdBy != null) {
            responseDto.setCreatedBy(createdBy.toCreatorDto());
        }
        if (updatedBy != null) {
            responseDto.setUpdatedBy(updatedBy.toCreatorDto());
        }
        responseDto.setWeight(weight);
        responseDto.setHeight(height);
        responseDto.setLength(length);
        responseDto.setFeedingGuide(feedingGuide);

        List<MealResponseDto> mealResponseDtoList = new ArrayList<>();
        for (Meal meal : meals) {
            mealResponseDtoList.add(meal.toResponseDto());
        }
        responseDto.setMeals(mealResponseDtoList);
        return responseDto;
    }
}
