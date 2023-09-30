package com.nhom3.zoomanagement.animals;

import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/animal")
public class AnimalController {
    @Autowired
    AnimalService service;

    @PostMapping
    public CreateAnimalDTO addAnimalDTO (@RequestBody @Valid CreateAnimalDTO dto){
        return service.addAnimal(dto);
    }

}
