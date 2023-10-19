package com.nhom3.zoomanagement.utils.search_filter;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class SearchCriteria {
    
    private String filterKey;
    
    private Object value;
    
    private String operation;
}
