package com.nhom3.zoomanagement.animals;

import com.nhom3.zoomanagement.errors.AppServiceException;
import com.nhom3.zoomanagement.errors.BadRequestException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
@RestController
@RequestMapping("/animal")
public class AnimalController implements IAnimalController{
    @Autowired
    AnimalService animalService;
    @Override
    public List<AnimalDTO> get() {
       List<AnimalDTO> animalDTOList = animalService.get();
       return animalDTOList;
    }

    @Override
    public AnimalDTO get(Integer id) throws AppServiceException {
        AnimalDTO animalDTO = animalService.get(id);
        return animalDTO;
    }

    @PreAuthorize("hasAnyAuthority({'STAFF', 'ADMIN'})")
    @Override
    public AnimalDTO create(CreateAnimalDTO dto) throws BadRequestException {
        AnimalDTO animalDTO = animalService.create(dto);
        return animalDTO;
    }

    @PreAuthorize("hasAnyAuthority({'STAFF', 'ADMIN'})")
    @Override
    public AnimalDTO update(Integer id, UpdateAnimalDTO dto) throws BadRequestException {
        AnimalDTO animalDTO = animalService.update(id, dto);
        return animalDTO;
    }

    @Override
    public AnimalDTO delete(Integer id) {
        return null;
    }
}
