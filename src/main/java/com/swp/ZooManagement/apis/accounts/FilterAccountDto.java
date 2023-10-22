package com.swp.ZooManagement.apis.accounts;

import com.swp.ZooManagement.core.FilterDtoBase;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;

public class FilterAccountDto extends FilterDtoBase<Account> {
    public FilterAccountDto(Integer page, Integer perPage) {
        super(page, perPage);
    }

    @Override
    public Account toEntity() {
        return null;
    }

    @Override
    public PageRequest getPageRequest() {
        return super.getPageRequest().withSort(Sort.by(Sort.Order.asc("fname")));
    }
}
