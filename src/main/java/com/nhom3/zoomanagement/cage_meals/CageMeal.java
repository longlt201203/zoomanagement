package com.nhom3.zoomanagement.cage_meals;

import com.nhom3.zoomanagement.accounts.Account;
import com.nhom3.zoomanagement.cages.Cage;
import com.nhom3.zoomanagement.meal_records.MealRecord;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.util.List;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@EntityListeners(AuditingEntityListener.class)
public class CageMeal {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column()
    private String time;

    @Column()
    private String food;

    @ManyToOne()
    private Cage cage;

    @ManyToOne
    @CreatedBy
    private Account createdBy;

    @OneToMany(mappedBy = "cageMeal")
    private List<MealRecord> mealRecordList;

}
