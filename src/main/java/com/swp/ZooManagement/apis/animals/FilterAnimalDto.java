package com.swp.ZooManagement.apis.animals;

import com.swp.ZooManagement.apis.animalspecies.AnimalSpecies;
import com.swp.ZooManagement.core.FilterDtoBase;

import java.beans.ConstructorProperties;

public class FilterAnimalDto extends FilterDtoBase<Animal> {
    protected Integer speciesId;

    @ConstructorProperties({ "page", "perPage", "speciesId" })
    public FilterAnimalDto(Integer page, Integer perPage, Integer speciesId) {
        super(page, perPage);
    }

    @Override
    public Animal toEntity() {
        Animal animal = new Animal();
        AnimalSpecies animalSpecies = new AnimalSpecies();
        animalSpecies.setId(speciesId);
        animal.setSpecies(animalSpecies);
        return animal;
    }
}
