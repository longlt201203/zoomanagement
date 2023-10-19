package com.nhom3.zoomanagement.news;

import com.nhom3.zoomanagement.accounts.Account;
import com.nhom3.zoomanagement.accounts.AccountSpecification;
import com.nhom3.zoomanagement.utils.search_filter.SearchCriteria;
import com.nhom3.zoomanagement.utils.search_filter.SpecificationBuilder;
import org.springframework.data.jpa.domain.Specification;

public class NewsSpecificationBuilder extends SpecificationBuilder<News> {

    @Override
    public Specification<News> build() {
        if (this.getParams().isEmpty()) {
            return null;
        }

        Specification<News> result = new NewsSpecification(this.getParams().get(0));
        for (int i = 1; i < this.getParams().size(); i++) {
            SearchCriteria criteria = this.getParams().get(i);
            result = Specification.where(result).and(new NewsSpecification(criteria));
        }

        return result;
    }
}
