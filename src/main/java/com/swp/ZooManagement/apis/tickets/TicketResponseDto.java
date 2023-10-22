package com.swp.ZooManagement.apis.tickets;

import com.swp.ZooManagement.apis.accounts.Account;
import com.swp.ZooManagement.apis.accounts.AccountCreatorDto;
import jakarta.persistence.*;
import lombok.Data;
import org.springframework.data.annotation.CreatedBy;

@Data
public class TicketResponseDto {
    private Integer id;
    private String name;
    private String description;
    private Double price;
    private AccountCreatorDto createdBy;
}
