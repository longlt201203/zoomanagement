package com.nhom3.zoomanagement.orders;

import com.nhom3.zoomanagement.utils.search_filter.SearchCriteria;
import com.nhom3.zoomanagement.utils.search_filter.SpecificationBuilder;
import org.springframework.data.jpa.domain.Specification;

public class MyOrderSpecificationBuilder extends SpecificationBuilder<MyOrder> {

    @Override
    public Specification<MyOrder> build() {
        if (this.getParams().isEmpty()) {
            return null;
        }

        Specification<MyOrder> result = new MyOrderSpecification(this.getParams().get(0));
        for (int i = 1; i < this.getParams().size(); i++) {
            SearchCriteria criteria = this.getParams().get(i);
            result = Specification.where(result).and(new MyOrderSpecification(criteria));
        }

        return result;
    }
}
