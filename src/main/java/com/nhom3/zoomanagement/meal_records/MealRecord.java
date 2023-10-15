package com.nhom3.zoomanagement.meal_records;

import com.nhom3.zoomanagement.accounts.Account;
import com.nhom3.zoomanagement.cage_meals.CageMeal;
import com.nhom3.zoomanagement.utils.Enums;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.ColumnDefault;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.util.Date;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@EntityListeners(AuditingEntityListener.class)
public class MealRecord {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(columnDefinition = "varchar(255) default 'NOT_FED_YET'")
    @Enumerated(EnumType.STRING)
    private Enums.MealStatusEnum status = Enums.MealStatusEnum.NOT_FED_YET;

    @ManyToOne
    @LastModifiedBy
    private Account updatedBy;

    @Column()
    @Temporal(TemporalType.TIMESTAMP)
    private Date updatedAt;

    @ManyToOne()
    private CageMeal cageMeal;
}
