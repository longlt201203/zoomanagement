package com.swp.ZooManagement.apis.cages;

import com.swp.ZooManagement.apis.accounts.Account;
import com.swp.ZooManagement.apis.animalspecies.AnimalSpecies;
import com.swp.ZooManagement.apis.areas.Area;
import com.swp.ZooManagement.core.DtoBase;
import jakarta.validation.constraints.*;
import lombok.Data;

@Data
public class UpdateCageDto implements DtoBase<Cage> {
    @NotBlank(message = "code field cannot be blank")
    @Size(max = 5, message = "code cannot be more than 5 characters!")
    @Pattern(regexp = "^[A-Za-z][0-9]{4}$", message = "Cage code must have format: Area Code + 4-digits number. Example: A0001, B0012")
    private String code;

    @NotNull(message = "Name cannot be blank")
    @NotBlank(message = "Name cannot be blank")
    @Size(min = 10, max = 255, message = "Name length must be between 5 and 255 characters")
    private String name;

    @NotNull(message = "Capacity cannot be null")
    @Min(value = 1, message = "Capacity of a cage must be between 1 and 30")
    @Max(value = 30, message = "Capacity of a cage must be between 1 and 30")
    private Integer capacity;

    private String description;

    @NotNull(message= "Area field can not be empty")
    private Integer areaId;

    private String managedById;

    @Override
    public Cage toEntity() {
        Cage cage = new Cage();
        cage.setCode(code);
        cage.setDescription(description);
        Area area = new Area();
        area.setId(areaId);
        cage.setArea(area);
        if (managedById != null) {
            Account account = new Account();
            account.setId(managedById);
            cage.setManagedBy(account);
        }
        cage.setName(name);
        cage.setCapacity(capacity);
        return cage;
    }
}
