package com.nhom3.zoomanagement.accounts;

import com.nhom3.zoomanagement.utils.search_filter.SearchCriteria;
import org.springframework.data.jpa.domain.Specification;

import java.util.ArrayList;
import java.util.List;

public class AccountSpecificationBuilder {
    private final List<SearchCriteria> params = new ArrayList<>();

    public final AccountSpecificationBuilder with(String key, String operation, Object value) {
        params.add(new SearchCriteria(key, value, operation));
        return this;
    }

    public final AccountSpecificationBuilder with(SearchCriteria searchCriteria) {
        params.add(searchCriteria);
        return this;
    }

    public Specification<Account> build() {
        if (params.isEmpty()) {
            return null;
        }
        
        Specification<Account> result = new AccountSpecification(params.get(0));
        for (int i = 1; i < params.size(); i++) {
            SearchCriteria criteria = params.get(i);
            result = Specification.where(result).and(new AccountSpecification(criteria));
        }
        
        return result;
    }
}
