package com.nhom3.zoomanagement.animals;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.nhom3.zoomanagement.accounts.AccountDTO;
import com.nhom3.zoomanagement.animal_images.AnimalImageDTO;
import com.nhom3.zoomanagement.animal_species.AnimalSpeciesDTO;
import com.nhom3.zoomanagement.cages.CageDTO;
import com.nhom3.zoomanagement.utils.Enums;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class AnimalDTO {
    public static AnimalDTO fromAnimal(Animal animal, boolean hasSpecies, boolean hasCage, boolean hasCreatedBy, boolean hasUpdatedBy) {
        AnimalDTO animalDTO = new AnimalDTO();
        animalDTO.setId(animal.getId());
        animalDTO.setName(animal.getName());
        animalDTO.setNation(animal.getNation());
        animalDTO.setDob(animal.getDob());
        animalDTO.setGender(animal.getGender());
        animalDTO.setStatus(animal.getStatus());
        animalDTO.setDescription(animal.getDescription());
        animalDTO.setNote(animal.getNote());
        animalDTO.setCreatedAt(animal.getCreatedAt());
        animalDTO.setUpdatedAt(animal.getUpdatedAt());
        animalDTO.setImageList(animal.getImageList());
        if(hasSpecies){
            animalDTO.setSpecies(AnimalSpeciesDTO.fromAnimalSpecies(animal.getSpecies(), false, false, false));
        }
        if(hasCage){
            animalDTO.setCage(CageDTO.fromCage(animal.getCage(),false, false, false, false, false, false));
        }
        if(hasCreatedBy){
            animalDTO.setCreatedBy(AccountDTO.fromAccount(animal.getCreatedBy(), false, false));
        }
        if(hasUpdatedBy){
            animalDTO.setUpdatedBy(AccountDTO.fromAccount(animal.getUpdatedBy(), false, false));
        }
        return animalDTO;
    }

    public static List<AnimalDTO> fromAnimalList(List<Animal> animalList, boolean hasSpecies, boolean hasCage, boolean hasCreatedBy, boolean hasUpdatedBy) {
        List<AnimalDTO> animalDTOList = new ArrayList<>();
        for (Animal animal : animalList) {
            AnimalDTO animalDTO = fromAnimal(animal, hasSpecies, hasCage, hasCreatedBy, hasUpdatedBy);
            animalDTOList.add(animalDTO);
        }
        return animalDTOList;
    }
    private Integer id;
    private String name;
    private String nation;
    private LocalDate dob;
    private Enums.AnimalGenderEnum gender;
    private Enums.AnimalStatusEnum status;
    private String description;
    private String note;
    private AnimalSpeciesDTO species;
    private CageDTO cage;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss.SSSSSS", timezone = "Asia/Ho_Chi_Minh")
    private Date createdAt;
    private AccountDTO createdBy;
    private AccountDTO updatedBy;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss.SSSSSS", timezone = "Asia/Ho_Chi_Minh")
    private Date updatedAt;
    private List<String> imageList;
}
