package com.swp.ZooManagement.apis.animalspecies;

import com.swp.ZooManagement.apis.accounts.Account;
import com.swp.ZooManagement.apis.animals.Animal;
import com.swp.ZooManagement.apis.animals.AnimalResponseDto;
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
public class AnimalSpecies implements ResponsableEntity<AnimalSpeciesResponseDto> {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(nullable = false, unique = true, columnDefinition = "NVARCHAR(60)")
    private String name;

    @Column(columnDefinition = "NVARCHAR(1000)")
    private String description;

    @Column(columnDefinition = "TEXT")
    private String image;

    @ManyToOne
    @CreatedBy
    private Account createdBy;

    @OneToMany(mappedBy = "species")
    private List<Animal> animals;

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
                animalResponseDtoList.add(animalResponseDto);
            }
        }
        responseDto.setAnimals(animalResponseDtoList);
        return responseDto;
    }
}
