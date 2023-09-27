package com.nhom3.zoomanagement.news;

import com.nhom3.zoomanagement.accounts.Account;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class News {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column
    private String content;

    @Column
    private String title;

    @Column
    private Date createdAt;
    
    @ManyToOne
    private Account creator;
}
