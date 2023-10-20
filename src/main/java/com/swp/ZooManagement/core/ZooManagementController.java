/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.swp.ZooManagement.core;

import com.swp.ZooManagement.errors.ZooManagementException;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

/**
 *
 * @author Le Thanh Long
 */
public interface ZooManagementController<EntityType, IdType, CreateDto extends DtoBase<EntityType>, UpdateDto extends DtoBase<EntityType>, FilterDto extends FilterDtoBase<EntityType>> {
    /**
     * Read this post please <a href="https://stackoverflow.com/questions/56468760/how-to-collect-all-fields-annotated-with-requestparam-into-one-object">Request Param Guide</a>
     * @param filter
     * @return
     */
    @GetMapping("/")
    GetManyResponse<EntityType> doGetMany(@Valid FilterDto filter) throws ZooManagementException;
    
    @GetMapping("/{id}")
    EntityType doGet(@PathVariable("id") IdType id) throws ZooManagementException;
    
    @PostMapping("/")
    EntityType doPost(@RequestBody @Valid CreateDto dto) throws ZooManagementException;
    
    @PutMapping("/{id}")
    EntityType doPut(@PathVariable("id") IdType id, @RequestBody @Valid UpdateDto dto) throws ZooManagementException;
    
    @DeleteMapping("/{id}")
    EntityType doDelete(@PathVariable("id") IdType id) throws ZooManagementException;
}
