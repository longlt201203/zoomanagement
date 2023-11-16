package com.swp.ZooManagement.apis.foods;

import com.swp.ZooManagement.core.FilterDtoBase;

public class FilterFoodDto extends FilterDtoBase<Food> {
    public FilterFoodDto(Integer page, Integer perPage) {
        super(page, perPage);
    }

    @Override
    public Food toEntity() {
        return null;
    }
}
