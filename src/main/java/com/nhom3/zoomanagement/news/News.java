package com.nhom3.zoomanagement.news;

import com.nhom3.zoomanagement.accounts.Account;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class News {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(nullable = false)
    private String content;

    @Column(nullable = false)
    private String title;

    @Column
    @CreationTimestamp
    private LocalDateTime postedAt;
    
    @ManyToOne(optional = false)
    private Account author;
}
