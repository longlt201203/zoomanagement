package com.nhom3.zoomanagement.orders;

import com.nhom3.zoomanagement.utils.Enums;
import com.nhom3.zoomanagement.utils.search_filter.SearchCriteria;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;
import org.springframework.data.jpa.domain.Specification;

public class MyOrderSpecification implements Specification<MyOrder> {
    private final SearchCriteria searchCriteria;

    public MyOrderSpecification(final SearchCriteria searchCriteria) {
        super();
        this.searchCriteria = searchCriteria;
    }

    @Override
    public Predicate toPredicate(Root<MyOrder> root,
                                 CriteriaQuery<?> query, CriteriaBuilder builder) {
        String strToSearch = searchCriteria.getValue().toString().toLowerCase();
        String field = searchCriteria.getFilterKey();
        Enums.SearchOperationEnum simpleOperation = Enums.SearchOperationEnum.getSimpleOperation(searchCriteria.getOperation());

        return switch (simpleOperation) {
            case CONTAINS -> builder.like(builder.lower(root.get(field).as(String.class)), "%" + strToSearch + "%");
            case EQUAL -> {
                if (field.equals("total")) yield builder.equal(root.get(field), strToSearch);
                yield builder.equal(root.get(field).as(String.class), strToSearch);
            }
            case GREATER_THAN -> {
                if (field.equals("total")) yield builder.greaterThan(root.get(field), strToSearch);
                yield builder.greaterThan(root.get(field).as(String.class), strToSearch);
            }
            case GREATER_THAN_EQUAL -> {
                if (field.equals("total")) yield builder.greaterThanOrEqualTo(root.get(field), strToSearch);
                yield builder.greaterThanOrEqualTo(root.get(field).as(String.class), strToSearch);
            }
            case LESS_THAN -> {
                if (field.equals("total")) yield builder.lessThan(root.get(field), strToSearch);
                yield builder.lessThan(root.get(field).as(String.class), strToSearch);
            }
            case LESS_THAN_EQUAL -> {
                if (field.equals("total")) yield builder.lessThanOrEqualTo(root.get(field), strToSearch);
                yield builder.lessThanOrEqualTo(root.get(field).as(String.class), strToSearch);
            }
        };
    }
}
