package com.nhom3.zoomanagement.cages;

import com.nhom3.zoomanagement.animal_species.AnimalSpecies;
import com.nhom3.zoomanagement.animals.Animal;
import com.nhom3.zoomanagement.areas.Area;
import com.nhom3.zoomanagement.meals.Meal;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
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
    private List<Meal> mealList;

}
