package com.swp.ZooManagement.apis.animals;

import com.swp.ZooManagement.core.FilterDtoBase;

public class FilterAnimalDto extends FilterDtoBase<Animal> {
    public FilterAnimalDto(Integer page, Integer perPage) {
        super(page, perPage);
    }

    @Override
    public Animal toEntity() {
        return null;
    }
}
