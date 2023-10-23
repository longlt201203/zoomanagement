/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.swp.ZooManagement.core;

import com.swp.ZooManagement.errors.ZooManagementException;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 *
 * @author Le Thanh Long
 */
public interface ZooManagementController<EntityType extends ResponsableEntity<ResponseType>, IdType, CreateDto extends DtoBase<EntityType>, UpdateDto extends DtoBase<EntityType>, FilterDto extends FilterDtoBase<EntityType>, ResponseType> {
    /**
     * Read this post please <a href="https://stackoverflow.com/questions/56468760/how-to-collect-all-fields-annotated-with-requestparam-into-one-object">Request Param Guide</a>
     * @param filter
     * @return
     */
    @GetMapping("/")
    List<ResponseType> doGetMany(@Valid FilterDto filter) throws ZooManagementException;
    
    @GetMapping("/{id}")
    ResponseType doGet(@PathVariable("id") IdType id) throws ZooManagementException;
    
    @PostMapping("/")
    ResponseType doPost(@Valid @RequestBody CreateDto dto) throws ZooManagementException;
    
    @PutMapping("/{id}")
    ResponseType doPut(@PathVariable("id") IdType id, @Valid @RequestBody UpdateDto dto) throws ZooManagementException;
    
    @DeleteMapping("/{id}")
    ResponseType doDelete(@PathVariable("id") IdType id) throws ZooManagementException;
}
