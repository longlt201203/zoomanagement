package com.swp.ZooManagement.apis.areas;

import com.swp.ZooManagement.apis.accounts.Account;
import com.swp.ZooManagement.apis.cages.Cage;
import com.swp.ZooManagement.core.ResponsableEntity;
import jakarta.persistence.*;
import lombok.Data;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.util.List;

@Entity
@EntityListeners(AuditingEntityListener.class)
@Data
public class Area implements ResponsableEntity<AreaResponseDto> {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(nullable = false, unique = true)
    private String code;

    @Column
    private String name;

    @Column
    private String location;

    @ManyToOne
    @CreatedBy
    private Account createdBy;

    @OneToMany(mappedBy = "area")
    private List<Cage> cages;

    @Override
    public AreaResponseDto toResponseDto() {
        AreaResponseDto responseDto = new AreaResponseDto();
        responseDto.setId(id);
        responseDto.setCode(code);
        responseDto.setName(name);
        responseDto.setLocation(location);
        if (createdBy != null) {
            responseDto.setCreatedBy(createdBy.toCreatorDto());
        }
        return responseDto;
    }
}
