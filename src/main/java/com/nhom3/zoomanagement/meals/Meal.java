package com.nhom3.zoomanagement.meals;

import com.nhom3.zoomanagement.cages.Cage;
import com.nhom3.zoomanagement.utils.Enums;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Meal {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column()
    private Date time;

    @Column()
    private String food;

    @Column()
    private String quantity;

    @Column(columnDefinition = "varchar(32) default 'NOT_FEEDED'")
    @Enumerated(EnumType.STRING)
    private Enums.MealStatusEnum status = Enums.MealStatusEnum.NOT_FEEDED;

    @Column()
    private String note;

    @ManyToOne()
    private Cage cage;
}
