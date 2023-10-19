package com.nhom3.zoomanagement.accounts;

import com.nhom3.zoomanagement.utils.search_filter.SearchCriteria;
import com.nhom3.zoomanagement.utils.search_filter.SpecificationBuilder;
import org.springframework.data.jpa.domain.Specification;


public class AccountSpecificationBuilder extends SpecificationBuilder<Account> {

    @Override
    public Specification<Account> build() {
        if (this.getParams().isEmpty()) {
            return null;
        }
        
        Specification<Account> result = new AccountSpecification(this.getParams().get(0));
        for (int i = 1; i < this.getParams().size(); i++) {
            SearchCriteria criteria = this.getParams().get(i);
            result = Specification.where(result).and(new AccountSpecification(criteria));
        }
        
        return result;
    }
}
