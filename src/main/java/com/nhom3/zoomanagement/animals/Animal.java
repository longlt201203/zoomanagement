package com.nhom3.zoomanagement.animals;

import com.nhom3.zoomanagement.animal_images.AnimalImage;
import com.nhom3.zoomanagement.animal_species.AnimalSpecies;
import com.nhom3.zoomanagement.cages.Cage;
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
    @Enumerated(EnumType.STRING)
    private Enums.AnimalGenderEnum gender;

    @Column(columnDefinition = "varchar(32) default 'HEALTHY'")
    @Enumerated(EnumType.STRING)
    private Enums.AnimalStatusEnum status = Enums.AnimalStatusEnum.HEALTHY;

    @Column()
    private String description;

    @Column()
    private String note;

    @ManyToOne()
    private AnimalSpecies species;

    @ManyToOne()
    private Cage cage;

    @OneToMany(mappedBy = "animal")
    private List<AnimalImage> imageList;


}
