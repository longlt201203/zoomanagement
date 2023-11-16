package com.swp.ZooManagement.apis.cages;

import com.swp.ZooManagement.apis.accounts.Account;
import com.swp.ZooManagement.apis.animals.Animal;
import com.swp.ZooManagement.apis.animals.AnimalResponseDto;
import com.swp.ZooManagement.apis.animalspecies.AnimalSpecies;
import com.swp.ZooManagement.apis.animalspecies.AnimalSpeciesResponseDto;
import com.swp.ZooManagement.apis.areas.Area;
import com.swp.ZooManagement.apis.cagemeals.CageMeal;
import com.swp.ZooManagement.apis.cagemeals.CageMealResponseDto;
import com.swp.ZooManagement.core.ResponsableEntity;
import jakarta.persistence.*;
import lombok.Data;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.util.ArrayList;
import java.util.List;

@Entity
@EntityListeners(AuditingEntityListener.class)
@Data
public class Cage implements ResponsableEntity<CageResponseDto> {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(nullable = false, unique = true)
    private String code;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false, columnDefinition = "INT DEFAULT 10")
    private Integer capacity;

    @Column(columnDefinition = "NVARCHAR(1000)")
    private String description;

    @ManyToOne
    private Area area;

    @ManyToOne
    private Account managedBy;

    @ManyToOne
    @CreatedBy
    private Account createdBy;

    @OneToMany(mappedBy = "cage")
    private List<CageMeal> cageMeals;

    @OneToMany(mappedBy = "cage")
    private List<Animal> animals;

    @Override
    public CageResponseDto toResponseDto() {
        CageResponseDto responseDto = new CageResponseDto();
        responseDto.setId(id);
        responseDto.setCode(code);
        responseDto.setDescription(description);
        responseDto.setArea(area.toResponseDto());
        if (managedBy != null) {
            responseDto.setManagedBy(managedBy.toCreatorDto());
        }
        if (createdBy != null) {
            responseDto.setCreatedBy(createdBy.toCreatorDto());
        }
        List<AnimalResponseDto> animalResponseDtoList = new ArrayList<>();
        if (animals != null) {
            for (Animal animal : animals) {
                AnimalResponseDto animalResponseDto = new AnimalResponseDto();
                animalResponseDto.setId(animal.getId());
                animalResponseDto.setName(animal.getName());
                animalResponseDto.setNation(animal.getNation());
                animalResponseDto.setDob(animal.getDob());
                animalResponseDto.setGender(animal.getGender());
                animalResponseDto.setStatus(animal.getStatus());
                animalResponseDto.setDescription(animal.getDescription());
                animalResponseDto.setNote(animal.getNote());
                animalResponseDto.setImageList(animal.getImageList().isEmpty() ? List.of() : List.of(animal.getImageList().split(";")));
                animalResponseDto.setCreatedAt(animal.getCreatedAt());
                animalResponseDto.setUpdatedAt(animal.getUpdatedAt());
                AnimalSpeciesResponseDto animalSpeciesResponseDto = new AnimalSpeciesResponseDto();
                animalSpeciesResponseDto.setId(animal.getSpecies().getId());
                animalResponseDto.setName(animal.getSpecies().getName());
                animalResponseDtoList.add(animalResponseDto);
            }
        }
        responseDto.setAnimals(animalResponseDtoList);
        List<CageMealResponseDto> cageMealResponseDtoList = new ArrayList<>();
        if (cageMeals != null) {
            for (CageMeal cageMeal : cageMeals) {
                cageMealResponseDtoList.add(cageMeal.toResponseDto());
            }
        }
        responseDto.setCageMeals(cageMealResponseDtoList);
        responseDto.setName(name);
        responseDto.setCapacity(capacity);
        return responseDto;
    }
}
