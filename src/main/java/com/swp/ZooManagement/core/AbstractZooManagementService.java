/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.swp.ZooManagement.core;

import java.util.List;
import java.util.Optional;

import com.swp.ZooManagement.errors.EntityNotFoundErrorReport;
import com.swp.ZooManagement.errors.ZooManagementException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 *
 * @author Le Thanh Long
 */
public abstract class AbstractZooManagementService<EntityType, IdType, CreateDto extends DtoBase<EntityType>, UpdateDto extends DtoBase<EntityType>, FilterDto extends FilterDtoBase<EntityType>> implements ZooManagementService<EntityType, IdType, CreateDto, UpdateDto, FilterDto> {
    @Autowired
    protected JpaRepository<EntityType, IdType> repository;
    
    protected abstract void beforeCreate(EntityType entity) throws ZooManagementException;
    protected abstract void beforeUpdate(EntityType oldEntity, EntityType newEntity) throws ZooManagementException;
    
    @Override
    public List<EntityType> findAll(FilterDto dto) {
//        Page<EntityType> entityPage;
//        if (dto.toEntity() != null) {
//            entityPage = repository.findAll(Example.of(dto.toEntity()), dto.getPageRequest());
//        } else {
//            entityPage = repository.findAll(dto.getPageRequest());
//        }
        List<EntityType> entities;
        if (dto.toEntity() != null) {
            entities = repository.findAll(Example.of(dto.toEntity()));
        } else {
            entities = repository.findAll();
        }
        return entities;
    }

    @Override
    public EntityType findById(IdType id) throws ZooManagementException {
        Optional<EntityType> findResult = repository.findById(id);
        if (findResult.isPresent()) {
            return findResult.get();
        }
        throw new ZooManagementException(new EntityNotFoundErrorReport("id", id.toString()));
    }

    @Override
    public EntityType create(CreateDto dto) throws ZooManagementException {
        EntityType entity = dto.toEntity();
        beforeCreate(entity);
        entity = repository.save(entity);
        return entity;
    }

    @Override
    public EntityType update(IdType id, UpdateDto dto) throws ZooManagementException {
        EntityType oldEntity = findById(id);
        EntityType newEntity = dto.toEntity();
        beforeUpdate(oldEntity, newEntity);
        oldEntity = repository.save(oldEntity);
        return oldEntity;
    }
}
