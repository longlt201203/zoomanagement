/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.swp.ZooManagement.core;

import com.swp.ZooManagement.errors.ZooManagementException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;

/**
 *
 * @author Le Thanh Long
 */
public abstract class AbstractZooManagementController<EntityType, IdType, CreateDto extends DtoBase<EntityType>, UpdateDto extends DtoBase<EntityType>, FilterDto extends FilterDtoBase<EntityType>> implements ZooManagementController<EntityType, IdType, CreateDto, UpdateDto, FilterDto> {
    @Autowired
    protected ZooManagementService<EntityType, IdType, CreateDto, UpdateDto, FilterDto> service;
    
    @Override
    public GetManyResponse<EntityType> doGetMany(FilterDto filter) throws ZooManagementException {
        Page<EntityType> entityPage = service.findAll(filter);
        GetManyResponse<EntityType> response = new GetManyResponse<>();
        response.setPage(entityPage.getNumber()+1);
        response.setPerPage(entityPage.getSize());
        response.setTotalPage(entityPage.getTotalPages());
        response.setTotalRecord(entityPage.getTotalElements());
        response.setData(entityPage.toList());
        return response;
    }

    @Override
    public EntityType doGet(IdType id) throws ZooManagementException {
        EntityType entity = service.findById(id);
        return entity;
    }

    @Override
    public EntityType doPost(CreateDto dto) throws ZooManagementException {
        EntityType entity = service.create(dto);
        return entity;
    }

    @Override
    public EntityType doPut(IdType id, UpdateDto dto) throws ZooManagementException {
        EntityType entity = service.update(id, dto);
        return entity;
    }

    @Override
    public EntityType doDelete(IdType id) throws ZooManagementException {
        EntityType entity = service.delete(id);
        return entity;
    }
    
}
