package com.nhom3.zoomanagement.meal_schedules;

import com.nhom3.zoomanagement.cages.Cage;
import com.nhom3.zoomanagement.meals.Meal;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.List;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class MealSchedule {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column()
    private LocalDate date;

    @ManyToOne()
    private Cage cage;

    @OneToMany(mappedBy = "mealSchedule")
    private List<Meal> mealList;

}
