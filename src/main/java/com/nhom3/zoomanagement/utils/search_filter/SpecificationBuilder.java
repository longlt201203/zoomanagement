package com.nhom3.zoomanagement.utils.search_filter;

import com.nhom3.zoomanagement.accounts.AccountSpecificationBuilder;
import lombok.Data;
import org.springframework.data.jpa.domain.Specification;

import java.util.ArrayList;
import java.util.List;

@Data
public class SpecificationBuilder<T> {
    private final List<SearchCriteria> params = new ArrayList<>();

    public final SpecificationBuilder<T> with(String key, String operation, Object value) {
        params.add(new SearchCriteria(key, value, operation));
        return this;
    }

    public final SpecificationBuilder<T> with(SearchCriteria searchCriteria) {
        params.add(searchCriteria);
        return this;
    }

    public Specification<T> build() {
        return null;
    }
}
