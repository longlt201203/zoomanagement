package com.swp.ZooManagement.apis.meals;

import com.swp.ZooManagement.apis.foods.Food;
import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
public class MealDetail {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    @JoinColumn
    private Meal meal;

    @ManyToOne(optional = false)
    private Food food;

    @Column(nullable = false)
    private Double amount;
}
