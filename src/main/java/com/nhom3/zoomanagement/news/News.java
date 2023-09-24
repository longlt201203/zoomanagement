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
    
    private String content;
    
    private String title;
    
    private Date createdAt;
    
    @ManyToOne
    private Account creator;
}
