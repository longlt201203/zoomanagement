package com.nhom3.zoomanagement.utils;

import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.*;

import java.util.List;

public interface ICrudController<DtoType, IdType, CreateDtoType, UpdateDtoType> {
    @GetMapping("/")
    List<DtoType> get();

    @GetMapping("/{id}")
    DtoType get(@PathVariable("id") IdType id);

    @PostMapping("/")
    DtoType create(@RequestBody @Valid CreateDtoType dto);

    @PutMapping("/{id}")
    DtoType update(@PathVariable("id") IdType id, @RequestBody @Valid UpdateDtoType dto);

    @DeleteMapping("/{id}")
    DtoType delete(@PathVariable("id") IdType id);
}
