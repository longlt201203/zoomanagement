package com.swp.ZooManagement.apis.meals;

import com.swp.ZooManagement.apis.animals.Animal;
import com.swp.ZooManagement.core.FilterDtoBase;

import java.beans.ConstructorProperties;

public class FilterMealDto extends FilterDtoBase<Meal> {
    private Integer animalId;

    @ConstructorProperties({ "page", "perPage", "animalId" })
    public FilterMealDto(Integer page, Integer perPage, Integer animalId) {
        super(page, perPage);
        this.animalId = animalId;
    }

    @Override
    public Meal toEntity() {
        Meal meal = new Meal();
        Animal animal = new Animal();
        animal.setId(animalId);
        meal.setAnimal(animal);
        return meal;
    }
}
