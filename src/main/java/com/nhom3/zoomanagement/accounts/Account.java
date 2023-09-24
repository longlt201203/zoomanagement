package com.nhom3.zoomanagement.accounts;

import com.nhom3.zoomanagement.news.News;
import com.nhom3.zoomanagement.utils.Enums;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Account {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;

    private String name;
    
    private Enums.RoleEnum role;

    private Enums.HumanGenderEnum gender;
    
    private String email;
    
    private String phoneNumber;
    
    private String avatar;
    
    @OneToMany(mappedBy = "creator")
    private List<News> newsList;
    
}
