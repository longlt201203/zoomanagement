package com.nhom3.zoomanagement.utils;

import com.nhom3.zoomanagement.errors.BadRequestException;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.*;

import java.util.List;

public interface ICrudController<DtoType, IdType, CreateDtoType, UpdateDtoType> {
    @GetMapping("/")
    List<DtoType> get();

    @GetMapping("/{id}")
    DtoType get(@PathVariable("id") IdType id) throws BadRequestException;

    @PostMapping("/")
    DtoType create(@RequestBody @Valid CreateDtoType dto) throws BadRequestException;

    @PutMapping("/{id}")
    DtoType update(@PathVariable("id") IdType id, @RequestBody @Valid UpdateDtoType dto) throws BadRequestException;

    @DeleteMapping("/{id}")
    DtoType delete(@PathVariable("id") IdType id) throws BadRequestException;
}
