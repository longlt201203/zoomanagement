/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.swp.ZooManagement.core;

import org.springframework.data.domain.PageRequest;

/**
 *
 * @author Le Thanh Long
 */
public interface FilterDtoBase<EntityType> extends DtoBase<EntityType> {
    PageRequest getPageRequest();
}
