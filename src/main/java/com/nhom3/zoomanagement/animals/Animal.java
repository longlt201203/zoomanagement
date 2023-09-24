package com.nhom3.zoomanagement.animals;

import com.nhom3.zoomanagement.animalimages.AnimalImage;
import com.nhom3.zoomanagement.animalspecies.AnimalSpecie;
import com.nhom3.zoomanagement.utils.Enums;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.List;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Animal {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column()
    private String name;

    @Column()
    private String nation;

    @Column()
    private Date dob;

    @Column()
    private Enums.AnimalGenderEnum gender;

    @Column()
    private Enums.AnimalStatusEnum status;

    @Column()
    private String description;

    @Column()
    private String note;

    @ManyToOne()
    private AnimalSpecie specie;

    @OneToMany(mappedBy = "animal")
    private List<AnimalImage> imageList;


}
