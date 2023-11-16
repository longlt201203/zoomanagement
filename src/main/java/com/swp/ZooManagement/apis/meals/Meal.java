package com.swp.ZooManagement.apis.meals;

import com.swp.ZooManagement.apis.animals.Animal;
import com.swp.ZooManagement.apis.foods.Food;
import jakarta.persistence.*;
import lombok.Data;

import java.time.Instant;
import java.util.List;

@Entity
@Data
public class Meal {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne(optional = false)
    private Animal animal;

    @Column(nullable = false)
    private Instant time;

    @OneToMany(mappedBy = "meal", cascade = CascadeType.ALL)
    private List<MealDetail> details;
}
