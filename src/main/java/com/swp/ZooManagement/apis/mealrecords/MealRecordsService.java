package com.swp.ZooManagement.apis.mealrecords;

import com.swp.ZooManagement.apis.accounts.Account;
import com.swp.ZooManagement.apis.cagemeals.CageMeal;
import com.swp.ZooManagement.apis.cages.Cage;
import com.swp.ZooManagement.core.AbstractZooManagementService;
import com.swp.ZooManagement.errors.ZooManagementException;
import com.swp.ZooManagement.utils.enums.MealStatusEnum;
import org.springframework.data.domain.Example;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.ArrayList;
import java.util.List;

@Service
public class MealRecordsService extends AbstractZooManagementService<MealRecord, Integer, CreateMealRecordDto, UpdateMealRecordDto, FilterMealRecordDto> {
    @Override
    public List<MealRecord> findAll(FilterMealRecordDto filterMealRecordDto) {
        MealRecordsRepository repository = (MealRecordsRepository) this.repository;
        List<MealRecord> mealRecords;
        if (filterMealRecordDto.toEntity() != null) {
            MealRecord filterEntity = filterMealRecordDto.toEntity();
            mealRecords = repository.findAllMealRecordsOfCage(filterEntity.getCageMeal().getCage().getId(), filterEntity.getCreatedAt());
        } else {
            mealRecords = repository.findAll();
        }
        return mealRecords;
    }

    @Override
    protected void beforeCreate(MealRecord entity) throws ZooManagementException {
        entity.setStatus(MealStatusEnum.NOT_FEED);
    }

    @Override
    protected void beforeUpdate(MealRecord oldEntity, MealRecord newEntity) throws ZooManagementException {
        oldEntity.setStatus(newEntity.getStatus());
    }

    @Override
    public MealRecord delete(Integer id) throws ZooManagementException {
        return null;
    }
}
