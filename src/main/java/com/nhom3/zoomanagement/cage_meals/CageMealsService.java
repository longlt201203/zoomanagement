package com.nhom3.zoomanagement.cage_meals;

import com.nhom3.zoomanagement.cages.Cage;
import com.nhom3.zoomanagement.cages.CagesRepository;
import com.nhom3.zoomanagement.errors.AppServiceException;
import com.nhom3.zoomanagement.errors.BadRequestException;
import com.nhom3.zoomanagement.errors.ErrorReport;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CageMealsService implements ICageMealsService {
    @Autowired
    CageMealsRepository cageMealsRepository;
    @Autowired
    CagesRepository cagesRepository;

    @Override
    public List<CageMealDTO> get() {
       List<CageMeal> cageMealList = cageMealsRepository.findAll();
        return  CageMealDTO.fromCageMealList(cageMealList, true, true, true);
    }

    @Override
    public CageMealDTO get(Integer id) throws AppServiceException {
        CageMeal cageMeal = cageMealsRepository.findById(id).orElseThrow(() -> new AppServiceException(new ErrorReport("Cage Meal not found")));
        return CageMealDTO.fromCageMeal(cageMeal, true, true, true);
    }

    @Override
    public CageMealDTO create(CreateCageMealDTO dto) throws BadRequestException {
        CageMeal cageMeal = new CageMeal();
        Cage cage = cagesRepository.findById(dto.getCageId()).orElseThrow(() -> new AppServiceException(new ErrorReport("Cage not found")));;
        cageMeal.setTime(dto.getTime());
        cageMeal.setFood(dto.getFood());
        cageMeal.setCage(cage);
        cageMeal = cageMealsRepository.save(cageMeal);
        return CageMealDTO.fromCageMeal(cageMeal, true, false, true);
    }

    @Override
    public CageMealDTO update(Integer id, CreateCageMealDTO dto) throws BadRequestException {
        CageMeal cageMeal = cageMealsRepository.findById(id).orElseThrow(() -> new AppServiceException(new ErrorReport("Meal Schedule not found")));
        Cage cage = cagesRepository.findById(dto.getCageId()).orElseThrow(() -> new AppServiceException(new ErrorReport("Cage not found")));;
        cageMeal.setTime(dto.getTime());
        cageMeal.setFood(dto.getFood());
        cageMeal.setCage(cage);
        cageMeal = cageMealsRepository.save(cageMeal);
        return CageMealDTO.fromCageMeal(cageMeal, true, false, true);
    }

    @Override
    public CageMealDTO delete(Integer id) {
        return null;
    }
}
