package com.swp.ZooManagement.apis.animalspecies;

import com.swp.ZooManagement.core.FilterDtoBase;

public class FilterAnimalSpeciesDto extends FilterDtoBase<AnimalSpecies> {
    public FilterAnimalSpeciesDto(Integer page, Integer perPage) {
        super(page, perPage);
    }

    @Override
    public AnimalSpecies toEntity() {
        return null;
    }
}
