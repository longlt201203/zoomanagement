package com.nhom3.zoomanagement.areas;

import com.nhom3.zoomanagement.cages.Cage;
import jakarta.persistence.*;
import lombok.*;

import java.util.List;
@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Area {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(nullable = false)
    private String code;

    @Column()
    private String name;

    @Column()
    private String location;

    @OneToMany(mappedBy = "area")
    private List<Cage> cageList;

}
