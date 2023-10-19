package com.nhom3.zoomanagement.accounts;

import com.nhom3.zoomanagement.utils.Enums;
import com.nhom3.zoomanagement.utils.search_filter.SearchCriteria;
import jakarta.persistence.criteria.*;
import org.springframework.data.jpa.domain.Specification;

public class AccountSpecification implements Specification<Account> {

    private final SearchCriteria searchCriteria;

    public AccountSpecification(final SearchCriteria searchCriteria) {
        super();
        this.searchCriteria = searchCriteria;
    }

    @Override
    public Predicate toPredicate(Root<Account> root,
                                 CriteriaQuery<?> query, CriteriaBuilder builder) {
        String strToSearch = searchCriteria.getValue().toString().toLowerCase();
        String field = searchCriteria.getFilterKey();
        Enums.SearchOperationEnum simpleOperation = Enums.SearchOperationEnum.getSimpleOperation(searchCriteria.getOperation());


        return switch (simpleOperation) {
            case CONTAINS -> {
                if (field.equals("name")) {
                    Expression<String> fullNameExpression = builder.function("CONCAT", String.class, root.get("fName"), builder.literal(" "), root.get("lName"));
                    yield builder.like(builder.lower(fullNameExpression), "%" + strToSearch + "%");
                }
                yield builder.like(builder.lower(root.get(field).as(String.class)), "%" + strToSearch + "%");
            }
            case EQUAL -> builder.equal(root.get(field).as(String.class), strToSearch);
            case GREATER_THAN -> builder.greaterThan(root.get(field).as(String.class), strToSearch);
            case GREATER_THAN_EQUAL -> builder.greaterThanOrEqualTo(root.get(field).as(String.class), strToSearch);
            case LESS_THAN -> builder.lessThan(root.get(field).as(String.class), strToSearch);
            case LESS_THAN_EQUAL -> builder.lessThanOrEqualTo(root.get(field).as(String.class), strToSearch);
        };
    }
}
