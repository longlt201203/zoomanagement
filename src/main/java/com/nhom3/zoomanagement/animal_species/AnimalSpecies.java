package com.nhom3.zoomanagement.animal_species;

import com.nhom3.zoomanagement.accounts.Account;
import com.nhom3.zoomanagement.animals.Animal;
import com.nhom3.zoomanagement.cages.Cage;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.util.Date;
import java.util.List;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@EntityListeners(AuditingEntityListener.class)
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

    @ManyToOne
    @CreatedBy
    private Account createdBy;

}
