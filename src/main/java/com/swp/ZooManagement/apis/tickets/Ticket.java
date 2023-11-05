package com.swp.ZooManagement.apis.tickets;

import com.swp.ZooManagement.apis.accounts.Account;
import com.swp.ZooManagement.apis.accounts.AccountCreatorDto;
import com.swp.ZooManagement.core.ResponsableEntity;
import jakarta.persistence.*;
import lombok.Data;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

@Entity
@Data
public class Ticket implements ResponsableEntity<TicketResponseDto> {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(nullable = false)
    private String name;

    @Column()
    private String description;

    @Column(nullable = false)
    private Double price;

    @Override
    public TicketResponseDto toResponseDto() {
        TicketResponseDto responseDto = new TicketResponseDto();
        responseDto.setId(id);
        responseDto.setName(name);
        responseDto.setDescription(description);
        responseDto.setPrice(price);
        return responseDto;
    }
}