package com.nhom3.zoomanagement.cages;

import com.nhom3.zoomanagement.accounts.Account;
import com.nhom3.zoomanagement.animal_species.AnimalSpecies;
import com.nhom3.zoomanagement.animals.Animal;
import com.nhom3.zoomanagement.areas.Area;
import com.nhom3.zoomanagement.cage_meals.CageMeal;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.util.List;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@EntityListeners(AuditingEntityListener.class)
public class Cage {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(nullable = false)
    private String code;

    @Column()
    private String description;

    @ManyToOne()
    private Area area;

    @ManyToOne()
    private AnimalSpecies animalSpecies;

    @OneToMany(mappedBy = "cage")
    private List<Animal> animalList;

    @OneToMany(mappedBy = "cage")
    private List<CageMeal> mealScheduleList;

    @ManyToOne
    private Account managedBy;

    @ManyToOne
    @CreatedBy
    private Account createdBy;


}
