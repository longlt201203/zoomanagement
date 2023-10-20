/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.swp.ZooManagement.core;

import com.swp.ZooManagement.errors.ZooManagementException;
import org.springframework.data.domain.Page;

/**
 *
 * @author Le Thanh Long
 */
public interface ZooManagementService<EntityType, IdType, CreateDto extends DtoBase<EntityType>, UpdateDto extends DtoBase<EntityType>, FilterDto extends FilterDtoBase<EntityType>> {
    Page<EntityType> findAll(FilterDto dto);
    EntityType findById(IdType id) throws ZooManagementException;
    EntityType create(CreateDto dto) throws ZooManagementException;
    EntityType update(IdType id, UpdateDto dto) throws ZooManagementException;
    EntityType delete(IdType id) throws ZooManagementException;
}
