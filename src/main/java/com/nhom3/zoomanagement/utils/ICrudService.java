package com.nhom3.zoomanagement.utils;

import com.nhom3.zoomanagement.errors.BadRequestException;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.*;
import com.nhom3.zoomanagement.errors.AppServiceException;

import java.util.List;

public interface ICrudService<DtoType, IdType, CreateDtoType, UpdateDtoType> {
    List<DtoType> get();
    DtoType get(IdType id) throws AppServiceException;
    DtoType create(CreateDtoType dto) throws AppServiceException;
    DtoType update(IdType id, @RequestBody @Valid UpdateDtoType dto) throws AppServiceException;
    DtoType delete(IdType id) throws AppServiceException;
}