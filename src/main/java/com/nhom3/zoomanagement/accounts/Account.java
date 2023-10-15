package com.nhom3.zoomanagement.accounts;

import com.nhom3.zoomanagement.animal_species.AnimalSpecies;
import com.nhom3.zoomanagement.animals.Animal;
import com.nhom3.zoomanagement.areas.Area;
import com.nhom3.zoomanagement.cage_meals.CageMeal;
import com.nhom3.zoomanagement.cages.Cage;
import com.nhom3.zoomanagement.meal_records.MealRecord;
import com.nhom3.zoomanagement.news.News;
import com.nhom3.zoomanagement.utils.Enums;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.time.LocalDateTime;
import java.util.Collection;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@EntityListeners(AuditingEntityListener.class)
@Entity
public class Account implements UserDetails {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;

    @Column(nullable = false)
    private String fname;

    @Column(nullable = false)
    private String lname;

    @Column
    @Enumerated(EnumType.STRING)
    private Enums.RoleEnum role;

    @Column
    @Enumerated(EnumType.STRING)
    private Enums.AccountGenderEnum gender;

    @Column(unique = true, nullable = false)
    private String email;

    @Column(nullable = false)
    private String phone;

    @Column(nullable = true)
    private String avt;

    @Column
    @CreationTimestamp
    private LocalDateTime createdAt;

    @Column()
    @Enumerated(EnumType.STRING)
    private Enums.AccountStatusEnum status = Enums.AccountStatusEnum.ACTIVE;

    @ManyToOne
    @CreatedBy
    private Account createdBy;

    @OneToMany(mappedBy = "author")
    private List<News> newsList;

    @OneToMany(mappedBy = "createdBy")
    private List<AnimalSpecies> createdAnimalSpecies;

    @OneToMany(mappedBy = "createdBy")
    private List<Animal> createdAnimal;

    @OneToMany(mappedBy = "updatedBy")
    private List<Animal> updatedAnimal;

    @OneToMany(mappedBy = "createdBy")
    private List<Area> createdArea;

    @OneToMany(mappedBy = "createdBy")
    private List<Cage> createdCage;

    @OneToMany(mappedBy = "managedBy")
    private List<Cage> managedCage;

    @OneToMany(mappedBy = "createdBy")
    private List<CageMeal> createdCageMeals;

    @OneToMany(mappedBy = "updatedBy")
    private List<MealRecord> updatedMealRecord;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return List.of(new SimpleGrantedAuthority(role.toString()));
    }

    @Override
    public String getPassword() {
        return null;
    }

    @Override
    public String getUsername() {
        return email;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
