package com.swp.ZooManagement.apis.cages;

import com.swp.ZooManagement.apis.accounts.Account;
import com.swp.ZooManagement.apis.animalspecies.AnimalSpecies;
import com.swp.ZooManagement.apis.areas.Area;
import com.swp.ZooManagement.core.DtoBase;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class CreateCageDto implements DtoBase<Cage> {
    @NotBlank(message = "code field cannot be blank")
    @Size(max = 5, message = "code cannot be more than 5 characters!")
    @Pattern(regexp = "^[A-Za-z][0-9]{4}$", message = "Cage code must have format: Area Code + 4-digits number. Example: A0001, B0012")
    private String code;

    private String description;

    @NotNull(message= "Area field can not be empty")
    private Integer areaId;

    @NotNull(message= "Animal Species field can not be empty")
    private Integer animalSpeciesId;
    private String managedById;

    @Override
    public Cage toEntity() {
        Cage cage = new Cage();
        cage.setCode(code);
        cage.setDescription(description);
        Area area = new Area();
        area.setId(areaId);
        cage.setArea(area);
        AnimalSpecies animalSpecies = new AnimalSpecies();
        animalSpecies.setId(animalSpeciesId);
        cage.setAnimalSpecies(animalSpecies);
        if (managedById != null) {
            Account account = new Account();
            account.setId(managedById);
            cage.setManagedBy(account);
        }
        return cage;
    }
}
