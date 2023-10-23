package com.swp.ZooManagement.apis.meals;

import com.swp.ZooManagement.apis.accounts.Account;
import com.swp.ZooManagement.apis.cages.Cage;
import jakarta.persistence.*;
import lombok.Data;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.Instant;

@Entity
@EntityListeners(AuditingEntityListener.class)
@Data
public class CageMeal {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(nullable = false)
    private Instant time;

    @Column(nullable = false)
    private String food;

    @CreatedBy
    @Column(updatable = false)
    private Account createdBy;

    @ManyToOne(optional = false)
    private Cage cage;
}
