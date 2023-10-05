package com.nhom3.zoomanagement.utils;

import java.util.List;

public interface ICrudService<DtoType, IdType, CreateDtoType, UpdateDtoType> {

    List<DtoType> get();
    DtoType get(IdType id);
    DtoType create(CreateDtoType dto);
    DtoType update(IdType id, UpdateDtoType dto);
    DtoType delete(IdType id);
}
