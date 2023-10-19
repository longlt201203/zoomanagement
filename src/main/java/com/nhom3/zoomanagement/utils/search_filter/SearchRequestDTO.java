package com.nhom3.zoomanagement.utils.search_filter;

import lombok.Data;

import java.util.List;

@Data
public class SearchRequestDTO {
    List<SearchCriteria> searchCriteriaList;
}
