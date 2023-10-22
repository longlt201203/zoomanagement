package com.swp.ZooManagement.apis.areas;

import com.swp.ZooManagement.core.FilterDtoBase;

public class FilterAreaDto extends FilterDtoBase<Area> {
    public FilterAreaDto(Integer page, Integer perPage) {
        super(page, perPage);
    }

    @Override
    public Area toEntity() {
        return null;
    }
}
