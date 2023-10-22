package com.swp.ZooManagement.apis.cages;

import com.swp.ZooManagement.core.FilterDtoBase;

public class FilterCageDto extends FilterDtoBase<Cage> {
    public FilterCageDto(Integer page, Integer perPage) {
        super(page, perPage);
    }

    @Override
    public Cage toEntity() {
        return null;
    }
}
