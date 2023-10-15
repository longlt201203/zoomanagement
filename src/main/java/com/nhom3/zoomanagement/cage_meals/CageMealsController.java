package com.nhom3.zoomanagement.cage_meals;

import com.nhom3.zoomanagement.errors.AppServiceException;
import com.nhom3.zoomanagement.errors.BadRequestException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/cage-meal")
public class CageMealsController implements ICageMealsController {
    @Autowired
    CageMealsService cageMealsService;

    @Override
    public List<CageMealDTO> get() {
        List<CageMealDTO> mealScheduleList = cageMealsService.get();
        return mealScheduleList;
    }

    @Override
    public CageMealDTO get(Integer id) throws AppServiceException {
        CageMealDTO mealScheduleDTO = cageMealsService.get(id);
        return mealScheduleDTO;
    }

    @PreAuthorize("hasAnyAuthority({'STAFF', 'ADMIN'})")
    @Override
    public CageMealDTO create(CreateCageMealDTO dto) throws BadRequestException {
        CageMealDTO mealScheduleDTO = cageMealsService.create(dto);
        return mealScheduleDTO;
    }

    @PreAuthorize("hasAnyAuthority({'STAFF', 'ADMIN'})")
    @Override
    public CageMealDTO update(Integer id, CreateCageMealDTO dto) throws BadRequestException {
        CageMealDTO mealScheduleDTO = cageMealsService.update(id, dto);
        return mealScheduleDTO;
    }

    @Override
    public CageMealDTO delete(Integer id) {
        return null;
    }
}
