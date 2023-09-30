package com.nhom3.zoomanagement.animals;

import org.springframework.stereotype.Service;

@Service
public class AnimalService {
    public CreateAnimalDTO addAnimal (CreateAnimalDTO dto){
        CreateAnimalDTO animal = new CreateAnimalDTO();
        animal.setName(dto.getName());
        animal.setNation(dto.getNation());
        animal.setDob(dto.getDob());
        animal.setDescription(dto.getDescription());
        animal.setNote(dto.getNote());
        animal.setStatus(dto.getStatus());
        animal.setGender(dto.getGender());
        animal.setSpecies(dto.getSpecies());
        animal.setImageList(dto.getImageList());
        return animal;
    }
}
