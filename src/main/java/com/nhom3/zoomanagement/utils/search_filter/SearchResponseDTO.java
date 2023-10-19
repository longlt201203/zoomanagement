package com.nhom3.zoomanagement.utils.search_filter;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;

@Data
@AllArgsConstructor
public class SearchResponseDTO<T> {
    
    private List<T> content;
    
    private Integer totalPages;
    
    private Integer totalElements;
    
    private Integer currentPage;
}
