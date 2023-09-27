package com.nhom3.zoomanagement.animal_species;

import com.nhom3.zoomanagement.animals.Animal;
import com.nhom3.zoomanagement.cages.Cage;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class AnimalSpecies {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column()
    private String name;

    @Column()
    private String description;

    @Column()
    private String image;

    @OneToMany(mappedBy = "animalSpecies")
    private List<Cage> cageList;

    @OneToMany(mappedBy = "species")
    private List<Animal> animalList;
}
