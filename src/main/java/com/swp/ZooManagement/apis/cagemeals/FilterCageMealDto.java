package com.swp.ZooManagement.apis.cagemeals;

import com.swp.ZooManagement.core.FilterDtoBase;

public class FilterCageMealDto extends FilterDtoBase<CageMeal> {
    public FilterCageMealDto(Integer page, Integer perPage) {
        super(page, perPage);
    }

    @Override
    public CageMeal toEntity() {
        return null;
    }
}
