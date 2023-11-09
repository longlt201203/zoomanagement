package com.swp.ZooManagement.apis.cagemeals;

import com.swp.ZooManagement.apis.cages.Cage;
import com.swp.ZooManagement.core.FilterDtoBase;

import java.beans.ConstructorProperties;

public class FilterCageMealDto extends FilterDtoBase<CageMeal> {
    private Integer cageId;

    @ConstructorProperties({ "page", "perPage", "cageId" })
    public FilterCageMealDto(Integer page, Integer perPage, Integer cageId) {
        super(page, perPage);
        this.cageId = cageId;
    }

    @Override
    public CageMeal toEntity() {
        CageMeal cageMeal = new CageMeal();
        Cage cage = new Cage();
        cage.setId(cageId);
        cageMeal.setCage(cage);
        return cageMeal;
    }
}
