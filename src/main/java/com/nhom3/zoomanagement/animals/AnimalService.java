package com.nhom3.zoomanagement.animals;

import com.nhom3.zoomanagement.animal_species.AnimalSpecies;
import com.nhom3.zoomanagement.animal_species.AnimalSpeciesRepository;
import com.nhom3.zoomanagement.cages.Cage;
import com.nhom3.zoomanagement.cages.CagesRepository;
import com.nhom3.zoomanagement.errors.AppServiceException;
import com.nhom3.zoomanagement.errors.BadRequestException;
import com.nhom3.zoomanagement.errors.ErrorReport;
import com.nhom3.zoomanagement.utils.Enums;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
@Service
public class AnimalService implements IAnimalService{
    @Autowired
    AnimalsRepository animalsRepository;
    @Autowired
    AnimalSpeciesRepository animalSpeciesRepository;
    @Autowired
    CagesRepository cagesRepository;

    @Override
    public List<AnimalDTO> get() {
        List<Animal> animalList = animalsRepository.findAll();
        return AnimalDTO.fromAnimalList(animalList, true, true, true, true);
    }

    @Override
    public AnimalDTO get(Integer id) throws AppServiceException {
        Animal animal = animalsRepository.findById(id).orElseThrow(() -> new AppServiceException(new ErrorReport("Animal not found")));
        return AnimalDTO.fromAnimal(animal, true, true, true, true);
    }

    @Override
    public AnimalDTO create(CreateAnimalDTO dto) throws BadRequestException {
        Animal animal = new Animal();
        AnimalSpecies species = animalSpeciesRepository.findById(dto.getSpeciesId()).orElseThrow(() -> new AppServiceException(new ErrorReport("Animal Species not found")));
        Cage cage = cagesRepository.findById(dto.getCageId()).orElseThrow(() -> new AppServiceException(new ErrorReport("Cage not found")));
        if(cage.getAnimalSpecies().getId() != dto.getSpeciesId()){
            throw new AppServiceException(new ErrorReport("Cannot add Animal to this cage"));
        }
        animal.setName(dto.getName());
        animal.setNation(dto.getNation());
        animal.setDob(dto.parseDob());
        animal.setGender(Enums.AnimalGenderEnum.valueOf(dto.getGender()));
        animal.setStatus(Enums.AnimalStatusEnum.valueOf(dto.getStatus()));
        animal.setDescription(dto.getDescription());
        animal.setNote(dto.getNote());
        animal.setSpecies(species);
        animal.setCage(cage);
        animal.setCreatedAt(new Date());
        animal.setImageList(dto.getImageList());
        animal = animalsRepository.save(animal);
        return AnimalDTO.fromAnimal(animal, false, false, true, true);
    }

    @Override
    public AnimalDTO update(Integer id, UpdateAnimalDTO dto) throws BadRequestException {
        Animal animal = animalsRepository.findById(id).orElseThrow(() -> new AppServiceException(new ErrorReport("Animal not found")));;
        AnimalSpecies species = animalSpeciesRepository.findById(dto.getSpeciesId()).orElseThrow(() -> new AppServiceException(new ErrorReport("Animal Species not found")));
        Cage cage = cagesRepository.findById(dto.getCageId()).orElseThrow(() -> new AppServiceException(new ErrorReport("Cage not found")));
        if(cage.getAnimalSpecies().getId() != dto.getSpeciesId()){
            throw new AppServiceException(new ErrorReport("Cannot add Animal to this cage"));
        }
        animal.setName(dto.getName());
        animal.setNation(dto.getNation());
        animal.setDob(dto.parseDob());
        animal.setGender(Enums.AnimalGenderEnum.valueOf(dto.getGender()));
        animal.setStatus(Enums.AnimalStatusEnum.valueOf(dto.getStatus()));
        animal.setDescription(dto.getDescription());
        animal.setNote(dto.getNote());
        animal.setSpecies(species);
        animal.setCage(cage);
        animal.setImageList(dto.getImageList());
        animal.setUpdatedAt(new Date());
        animal = animalsRepository.save(animal);
        return AnimalDTO.fromAnimal(animal, false, false, true, true);
    }

    @Override
    public AnimalDTO delete(Integer id) {
        return null;
    }
}
