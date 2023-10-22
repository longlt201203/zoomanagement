package com.swp.ZooManagement.animals;

import com.swp.ZooManagement.apis.accounts.Account;
import com.swp.ZooManagement.apis.animalspecies.AnimalSpecies;
import com.swp.ZooManagement.apis.cages.Cage;
import com.swp.ZooManagement.utils.enums.AnimalGenderEnum;
import com.swp.ZooManagement.utils.enums.AnimalStatusEnum;
import jakarta.persistence.*;
import lombok.Data;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.Instant;

@Entity
@EntityListeners(AuditingEntityListener.class)
@Data
public class Animal {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column
    private String name;

    @Column
    private String nation;

    @Column
    private Instant dob;

    @Column
    private AnimalGenderEnum gender;

    @Column
    private AnimalStatusEnum status;

    @Column
    private String description;

    @Column
    private String note;

    @Column
    private String imageList;

    @ManyToOne
    private AnimalSpecies species;

    @ManyToOne
    private Cage cage;

    @ManyToOne
    @CreatedBy
    private Account createdBy;

    @Column
    @CreatedDate
    private Instant createdAt;

    @ManyToOne
    @LastModifiedBy
    private Account updatedBy;

    @Column
    @LastModifiedDate
    private Instant updatedAt;
}
