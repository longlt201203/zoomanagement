/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.swp.ZooManagement.core;

import com.swp.ZooManagement.errors.ZooManagementException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Le Thanh Long
 */
public abstract class AbstractZooManagementController<EntityType extends ResponsableEntity<ResponseType>, IdType, CreateDto extends DtoBase<EntityType>, UpdateDto extends DtoBase<EntityType>, FilterDto extends FilterDtoBase<EntityType>, ResponseType> implements ZooManagementController<EntityType, IdType, CreateDto, UpdateDto, FilterDto, ResponseType> {
    @Autowired
    protected ZooManagementService<EntityType, IdType, CreateDto, UpdateDto, FilterDto> service;
    
    @Override
    public GetManyResponse<ResponseType> doGetMany(FilterDto filter) throws ZooManagementException {
        Page<EntityType> entityPage = service.findAll(filter);
        GetManyResponse<ResponseType> response = new GetManyResponse<>();
        response.setPage(entityPage.getNumber()+1);
        response.setPerPage(entityPage.getSize());
        response.setTotalPage(entityPage.getTotalPages());
        response.setTotalRecord(entityPage.getTotalElements());
        List<ResponseType> objs = new ArrayList<>();
        for (EntityType entityType : entityPage.toList()) {
            objs.add(entityType.toResponseDto());
        }
        response.setData(objs);
        return response;
    }

    @Override
    public ResponseType doGet(IdType id) throws ZooManagementException {
        EntityType entity = service.findById(id);
        return entity.toResponseDto();
    }

    @Override
    public ResponseType doPost(CreateDto dto) throws ZooManagementException {
        EntityType entity = service.create(dto);
        return entity.toResponseDto();
    }

    @Override
    public ResponseType doPut(IdType id, UpdateDto dto) throws ZooManagementException {
        EntityType entity = service.update(id, dto);
        return entity.toResponseDto();
    }

    @Override
    public ResponseType doDelete(IdType id) throws ZooManagementException {
        EntityType entity = service.delete(id);
        return entity.toResponseDto();
    }
    
}
