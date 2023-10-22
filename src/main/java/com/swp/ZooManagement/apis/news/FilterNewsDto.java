package com.swp.ZooManagement.apis.news;

import com.swp.ZooManagement.core.FilterDtoBase;

public class FilterNewsDto extends FilterDtoBase<News> {
    public FilterNewsDto(Integer page, Integer perPage) {
        super(page, perPage);
    }

    @Override
    public News toEntity() {
        return null;
    }
}
