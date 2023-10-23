package com.swp.ZooManagement.apis.meals;

import com.swp.ZooManagement.apis.accounts.Account;
import com.swp.ZooManagement.utils.enums.MealStatusEnum;
import jakarta.persistence.*;
import lombok.Data;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.Instant;

@Entity
@EntityListeners(AuditingEntityListener.class)
@Data
public class Meal {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(nullable = false)
    private MealStatusEnum status = MealStatusEnum.NOT_FEED;

    @CreatedDate
    @Column(updatable = false)
    private Instant createdAt;

    @LastModifiedDate
    private Instant updatedAt;

    @LastModifiedBy
    private Account updatedBy;

    @ManyToOne(optional = false)
    private CageMeal cageMeal;
}
