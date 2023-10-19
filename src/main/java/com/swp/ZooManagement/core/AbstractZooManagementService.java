/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.swp.ZooManagement.core;

import com.swp.ZooManagement.errors.EntityNotFoundException;
import com.swp.ZooManagement.errors.ZooManagementServiceException;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 *
 * @author Le Thanh Long
 */
public abstract class AbstractZooManagementService<EntityType, IdType, CreateDto extends DtoBase<EntityType>, UpdateDto extends DtoBase<EntityType>, FilterDto extends FilterDtoBase<EntityType>> implements ZooManagementService<EntityType, IdType, CreateDto, UpdateDto, FilterDto> {
    @Autowired
    protected JpaRepository<EntityType, IdType> repository;
    
    protected abstract void berforeCreate(EntityType entity) throws ZooManagementServiceException;
    protected abstract void berforeUpdate(EntityType oldEntity, EntityType newEntity) throws ZooManagementServiceException;
    
    @Override
    public Page<EntityType> findAll(FilterDto dto) {
        Page<EntityType> entityPage;
        if (dto.toEntity() != null) {
            entityPage = repository.findAll(Example.of(dto.toEntity()), dto.getPageRequest());
        } else {
            entityPage = repository.findAll(dto.getPageRequest());
        }
        return entityPage;
    }

    @Override
    public EntityType findById(IdType id) throws ZooManagementServiceException {
        Optional<EntityType> findResult = repository.findById(id);
        if (findResult.isPresent()) {
            return findResult.get();
        }
        throw new EntityNotFoundException();
    }

    @Override
    public EntityType create(CreateDto dto) throws ZooManagementServiceException {
        EntityType entity = dto.toEntity();
        berforeCreate(entity);
        entity = repository.save(entity);
        return entity;
    }

    @Override
    public EntityType update(IdType id, UpdateDto dto) throws ZooManagementServiceException {
        EntityType oldEntity = findById(id);
        EntityType newEntity = dto.toEntity();
        berforeUpdate(oldEntity, newEntity);
        oldEntity = repository.save(oldEntity);
        return oldEntity;
    }
}
