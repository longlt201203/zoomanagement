package com.nhom3.zoomanagement.utils;

import java.util.List;

public interface ICrudService<EntityType, IdType, CreateDtoType, UpdateDtoType> {

    List<EntityType> get();
    EntityType get(IdType id);
    EntityType create(CreateDtoType dto);
    EntityType update(IdType id, UpdateDtoType dto);
    EntityType delete(IdType id);
}
