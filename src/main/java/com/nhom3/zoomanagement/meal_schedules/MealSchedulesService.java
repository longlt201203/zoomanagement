package com.nhom3.zoomanagement.meal_schedules;

import com.nhom3.zoomanagement.cages.Cage;
import com.nhom3.zoomanagement.cages.CagesRepository;
import com.nhom3.zoomanagement.errors.AppServiceException;
import com.nhom3.zoomanagement.errors.BadRequestException;
import com.nhom3.zoomanagement.errors.ErrorReport;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MealSchedulesService implements IMealSchedulesService{
    @Autowired
    MealSchedulesRepository mealSchedulesRepository;
    @Autowired
    CagesRepository cagesRepository;

    @Override
    public List<MealScheduleDTO> get() {
       List<MealSchedule> mealScheduleList = mealSchedulesRepository.findAll();
        return  MealScheduleDTO.fromMealScheduleList(mealScheduleList, true, true);
    }

    @Override
    public MealScheduleDTO get(Integer id) throws AppServiceException {
        MealSchedule mealSchedule = mealSchedulesRepository.findById(id).orElseThrow(() -> new AppServiceException(new ErrorReport("Meal Schedule not found")));
        return MealScheduleDTO.fromMealSchedule(mealSchedule, true, true);
    }

    @Override
    public MealScheduleDTO create(CreateMealScheduleDTO dto) throws BadRequestException {
        MealSchedule mealSchedule = new MealSchedule();
        Cage cage = cagesRepository.findById(dto.getCageId()).orElseThrow(() -> new AppServiceException(new ErrorReport("Cage not found")));;
        mealSchedule.setDate(dto.parseDate());
        mealSchedule.setCage(cage);
        mealSchedule = mealSchedulesRepository.save(mealSchedule);
        return MealScheduleDTO.fromMealSchedule(mealSchedule, true, false);
    }

    @Override
    public MealScheduleDTO update(Integer id, CreateMealScheduleDTO dto) throws BadRequestException {
        MealSchedule mealSchedule = mealSchedulesRepository.findById(id).orElseThrow(() -> new AppServiceException(new ErrorReport("Meal Schedule not found")));
        Cage cage = cagesRepository.findById(dto.getCageId()).orElseThrow(() -> new AppServiceException(new ErrorReport("Cage not found")));
        mealSchedule.setDate(dto.parseDate());
        mealSchedule.setCage(cage);
        mealSchedule = mealSchedulesRepository.save(mealSchedule);
        return MealScheduleDTO.fromMealSchedule(mealSchedule, true, true);
    }

    @Override
    public MealScheduleDTO delete(Integer id) {
        return null;
    }
}
