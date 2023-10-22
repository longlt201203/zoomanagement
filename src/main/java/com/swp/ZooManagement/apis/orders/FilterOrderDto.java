package com.swp.ZooManagement.apis.orders;

import com.swp.ZooManagement.core.FilterDtoBase;

public class FilterOrderDto extends FilterDtoBase<MyOrder> {
    public FilterOrderDto(Integer page, Integer perPage) {
        super(page, perPage);
    }

    @Override
    public MyOrder toEntity() {
        return null;
    }
}
