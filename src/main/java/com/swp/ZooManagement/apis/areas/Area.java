package com.swp.ZooManagement.apis.areas;

import com.swp.ZooManagement.apis.accounts.Account;
import com.swp.ZooManagement.apis.animals.Animal;
import com.swp.ZooManagement.apis.animals.AnimalResponseDto;
import com.swp.ZooManagement.apis.animalspecies.AnimalSpeciesResponseDto;
import com.swp.ZooManagement.apis.cages.Cage;
import com.swp.ZooManagement.apis.cages.CageResponseDto;
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
public class Area implements ResponsableEntity<AreaResponseDto> {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(nullable = false, unique = true)
    private String code;

    @Column(columnDefinition = "NVARCHAR(60)")
    private String name;

    @Column(columnDefinition = "NVARCHAR(255)")
    private String location;

    @ManyToOne
    @CreatedBy
    private Account createdBy;

    @OneToMany(mappedBy = "area")
    private List<Cage> cages;

    @Override
    public AreaResponseDto toResponseDto() {
        AreaResponseDto responseDto = new AreaResponseDto();
        responseDto.setId(id);
        responseDto.setCode(code);
        responseDto.setName(name);
        responseDto.setLocation(location);
        if (createdBy != null) {
            responseDto.setCreatedBy(createdBy.toCreatorDto());
        }
        List<CageResponseDto> cageResponseDtoList = new ArrayList<>();
        if (cages != null) {
            for (Cage cage : cages) {
                CageResponseDto cageResponseDto = new CageResponseDto();
                cageResponseDto.setId(cage.getId());
                cageResponseDto.setCode(cage.getCode());
                cageResponseDto.setDescription(cage.getDescription());
                AnimalSpeciesResponseDto animalSpeciesResponseDto = new AnimalSpeciesResponseDto();
                animalSpeciesResponseDto.setId(cage.getAnimalSpecies().getId());
                animalSpeciesResponseDto.setName(cage.getAnimalSpecies().getName());
                cageResponseDto.setAnimalSpecies(animalSpeciesResponseDto);
                List<AnimalResponseDto> animalResponseDtoList = new ArrayList<>();
                if (cage.getAnimals() != null) {
                    for (Animal animal : cage.getAnimals()) {
                        AnimalResponseDto animalResponseDto = new AnimalResponseDto();
                        animalResponseDto.setId(animal.getId());
                        animalResponseDtoList.add(animalResponseDto);
                    }

                }
                cageResponseDto.setAnimals(animalResponseDtoList);
                cageResponseDtoList.add(cageResponseDto);
                if (cage.getManagedBy() != null) {
                    cageResponseDto.setManagedBy(cage.getManagedBy().toCreatorDto());
                }
            }
        }
        responseDto.setCages(cageResponseDtoList);
        return responseDto;
    }
}
