package com.nhom3.zoomanagement.news;

import com.nhom3.zoomanagement.accounts.Account;
import com.nhom3.zoomanagement.utils.Enums;
import com.nhom3.zoomanagement.utils.search_filter.SearchCriteria;
import jakarta.persistence.criteria.*;
import org.springframework.data.jpa.domain.Specification;

public class NewsSpecification implements Specification<News> {
    private final SearchCriteria searchCriteria;

    public NewsSpecification(final SearchCriteria searchCriteria) {
        super();
        this.searchCriteria = searchCriteria;
    }

    @Override
    public Predicate toPredicate(Root<News> root,
                                 CriteriaQuery<?> query, CriteriaBuilder builder) {
        String strToSearch = searchCriteria.getValue().toString().toLowerCase();
        String field = searchCriteria.getFilterKey();
        Enums.SearchOperationEnum simpleOperation = Enums.SearchOperationEnum.getSimpleOperation(searchCriteria.getOperation());
        
        return switch (simpleOperation) {
            case CONTAINS -> {
                if(field.equals("authorName")) {
                    Expression<String> fullNameExpression = builder.lower(builder.function("CONCAT", String.class, authorJoin(root).get("fName"), builder.literal(" "), authorJoin(root).get("lName")));
                    yield builder.like(fullNameExpression, "%" + strToSearch + "%");
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

    private Join<News,Account> authorJoin(Root<News> root){
        return root.join("author");
    }
}
