package com.swp.ZooManagement.apis.cages;

import com.swp.ZooManagement.animals.Animal;
import com.swp.ZooManagement.apis.accounts.Account;
import com.swp.ZooManagement.apis.animalspecies.AnimalSpecies;
import com.swp.ZooManagement.apis.areas.Area;
import com.swp.ZooManagement.core.ResponsableEntity;
import jakarta.persistence.*;
import lombok.Data;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

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

    @Column
    private String description;

    @ManyToOne
    private Area area;

    @ManyToOne
    private AnimalSpecies animalSpecies;

    @ManyToOne
    private Account managedBy;

    @ManyToOne
    @CreatedBy
    private Account createdBy;

    @OneToMany(mappedBy = "cage")
    private List<Animal> animals;

    @Override
    public CageResponseDto toResponseDto() {
        CageResponseDto responseDto = new CageResponseDto();
        responseDto.setId(id);
        responseDto.setCode(code);
        responseDto.setDescription(description);
        responseDto.setArea(area.toResponseDto());
        responseDto.setAnimalSpecies(animalSpecies.toResponseDto());
        if (managedBy != null) {
            responseDto.setManagedBy(managedBy.toCreatorDto());
        }
        if (createdBy != null) {
            responseDto.setCreatedBy(createdBy.toCreatorDto());
        }
        return responseDto;
    }
}
