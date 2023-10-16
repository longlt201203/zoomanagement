package com.nhom3.zoomanagement.tickets;

import com.nhom3.zoomanagement.accounts.Account;
import com.nhom3.zoomanagement.utils.Enums;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

@Data
@NoArgsConstructor
@AllArgsConstructor
@EntityListeners(AuditingEntityListener.class)
@Entity
public class Ticket {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    
    @Column(nullable = false)
    private String name;
    
    @Column()
    private String description;

    @Column(nullable = false)
    private Integer price;
    
    @ManyToOne
    @CreatedBy
    private Account createdBy;
}
