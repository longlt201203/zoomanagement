/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.swp.ZooManagement.core;

import lombok.Data;
import org.springframework.data.domain.PageRequest;

import java.beans.ConstructorProperties;

/**
 *
 * @author Le Thanh Long
 */
@Data
public abstract class FilterDtoBase<EntityType> implements DtoBase<EntityType> {
    protected int page;
    protected int perPage;
    public PageRequest getPageRequest() {
        return PageRequest.of(page-1, perPage);
    }

    @ConstructorProperties({ "page", "perPage" })
    public FilterDtoBase(Integer page, Integer perPage) {
        this.page = page != null ? page : 1;
        this.perPage = perPage != null ? perPage : 10;
    }
}
