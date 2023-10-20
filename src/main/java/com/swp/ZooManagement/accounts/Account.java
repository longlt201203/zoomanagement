/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.swp.ZooManagement.accounts;

import com.swp.ZooManagement.utils.enums.AccountGenderEnum;
import com.swp.ZooManagement.utils.enums.AccountRoleEnum;
import com.swp.ZooManagement.utils.enums.AccountStatusEnum;
import jakarta.persistence.*;
import lombok.Data;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.Instant;

/**
 *
 * @author Le Thanh Long
 */
@Entity
@EntityListeners(AuditingEntityListener.class)
@Data
public class Account {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;

    @Column(nullable = false, unique = true, length = 60)
    private String email;

    @Column(nullable = false)
    private AccountRoleEnum role;

    @Column(nullable = false)
    private AccountGenderEnum gender;

    @Column(nullable = false, length = 30)
    private String fname;

    @Column(nullable = false, length = 30)
    private String lname;

    @Column(nullable = false, unique = true, length = 20)
    private String phone;

    @Column(nullable = false)
    private String avt;

    @Column(nullable = false, updatable = false)
    @CreatedDate
    private Instant createdAt;

    @Column(nullable = false)
    private AccountStatusEnum status = AccountStatusEnum.ACTIVE;

    @CreatedBy
    @OneToOne(fetch = FetchType.LAZY)
    private Account createdBy;
}
