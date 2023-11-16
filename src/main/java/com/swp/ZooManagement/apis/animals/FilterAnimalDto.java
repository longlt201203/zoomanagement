package com.swp.ZooManagement.apis.animals;

import com.swp.ZooManagement.apis.accounts.Account;
import com.swp.ZooManagement.apis.animalspecies.AnimalSpecies;
import com.swp.ZooManagement.apis.cages.Cage;
import com.swp.ZooManagement.core.FilterDtoBase;

import java.beans.ConstructorProperties;

public class FilterAnimalDto extends FilterDtoBase<Animal> {
    protected Integer speciesId;
    protected Integer cageId;
    private String managerId;

    public void setManagerId(String managerId) {
        this.managerId = managerId;
    }

    @ConstructorProperties({ "page", "perPage", "speciesId", "cageId" })
    public FilterAnimalDto(Integer page, Integer perPage, Integer speciesId, Integer cageId) {
        super(page, perPage);
        this.speciesId = speciesId;
        this.cageId = cageId;
    }

    @Override
    public Animal toEntity() {
        Animal animal = new Animal();
        AnimalSpecies animalSpecies = new AnimalSpecies();
        animalSpecies.setId(speciesId);
        animal.setSpecies(animalSpecies);
        if (managerId != null || cageId != null) {
            Cage cage = new Cage();
            Account manager = new Account();
            manager.setId(managerId);
            cage.setManagedBy(manager);
            cage.setId(cageId);
            animal.setCage(cage);
        }
        return animal;
    }
}
