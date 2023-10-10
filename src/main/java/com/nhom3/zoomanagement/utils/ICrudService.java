package com.nhom3.zoomanagement.utils;

import com.nhom3.zoomanagement.errors.AppServiceException;
import com.nhom3.zoomanagement.errors.BadRequestException;

import java.util.List;

public interface ICrudService<DtoType, IdType, CreateDtoType, UpdateDtoType> {

    List<DtoType> get();
    DtoType get(IdType id) throws AppServiceException;
    DtoType create(CreateDtoType dto) throws BadRequestException;
    DtoType update(IdType id, UpdateDtoType dto) throws BadRequestException;
    DtoType delete(IdType id);
}
