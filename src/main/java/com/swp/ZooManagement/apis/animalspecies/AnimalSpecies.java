package com.swp.ZooManagement.apis.animalspecies;

import com.swp.ZooManagement.apis.accounts.Account;
import com.swp.ZooManagement.core.ResponsableEntity;
import jakarta.persistence.*;
import lombok.Data;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

@Entity
@EntityListeners(AuditingEntityListener.class)
@Data
public class AnimalSpecies implements ResponsableEntity<AnimalSpeciesResponseDto> {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(nullable = false, unique = true)
    private String name;

    @Column
    private String description;

    @Column
    private String image;

    @ManyToOne
    @CreatedBy
    private Account createdBy;

    @Override
    public AnimalSpeciesResponseDto toResponseDto() {
        AnimalSpeciesResponseDto responseDto = new AnimalSpeciesResponseDto();
        responseDto.setId(id);
        responseDto.setName(name);
        responseDto.setImage(image);
        responseDto.setDescription(description);
        if (createdBy != null) {
            responseDto.setCreatedBy(createdBy.toCreatorDto());
        }
        return responseDto;
    }
}
