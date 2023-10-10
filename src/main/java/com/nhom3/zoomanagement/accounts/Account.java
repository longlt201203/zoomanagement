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

    @Column
    private String name;

    @Column
    @Enumerated(EnumType.STRING)
    private Enums.RoleEnum role;

    @Column
    @Enumerated(EnumType.STRING)
    private Enums.HumanGenderEnum gender;

    @Column(unique = true)
    private String email;

    @Column
    private String phoneNumber;

    @Column
    private String avatar;

    @OneToMany(mappedBy = "creator")
    private List<News> newsList;
}
