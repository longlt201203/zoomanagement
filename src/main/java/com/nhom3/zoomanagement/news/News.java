package com.nhom3.zoomanagement.news;

import com.nhom3.zoomanagement.accounts.Account;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDateTime;
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
    @CreationTimestamp
    private LocalDateTime createdAt;
    
    @ManyToOne
    private Account creator;
}
