package com.nhom3.zoomanagement.meals;

import com.nhom3.zoomanagement.cages.Cage;
import com.nhom3.zoomanagement.meal_schedules.MealSchedule;
import com.nhom3.zoomanagement.utils.Enums;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.ColumnDefault;

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
    private String time;

    @Column()
    private String food;

    @Column()
    @Enumerated(EnumType.STRING)
    @ColumnDefault("NOT_FED_YET")
    private Enums.MealStatusEnum status = Enums.MealStatusEnum.NOT_FED_YET;

    @ManyToOne()
    private MealSchedule mealSchedule;
}
