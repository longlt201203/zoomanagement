package com.swp.ZooManagement.apis.cages;

import com.swp.ZooManagement.apis.animalspecies.AnimalSpecies;
import com.swp.ZooManagement.core.FilterDtoBase;

import java.beans.ConstructorProperties;

public class FilterCageDto extends FilterDtoBase<Cage> {
    protected Integer speciesId;

    @ConstructorProperties({ "page", "perPage", "speciesId" })
    public FilterCageDto(Integer page, Integer perPage, Integer speciesId) {
        super(page, perPage);
        this.speciesId = speciesId;
    }

    @Override
    public Cage toEntity() {
        Cage cage = new Cage();
        AnimalSpecies animalSpecies = new AnimalSpecies();
        animalSpecies.setId(speciesId);
        cage.setAnimalSpecies(animalSpecies);
        return cage;
    }
}
