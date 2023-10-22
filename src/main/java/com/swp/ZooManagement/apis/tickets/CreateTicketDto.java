package com.swp.ZooManagement.apis.tickets;

import com.swp.ZooManagement.core.DtoBase;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class CreateTicketDto implements DtoBase<Ticket> {
    @NotBlank(message = "Name1 must be not blank")
    private String name;

    private String description;

    @Min(value = 0, message = "The smallest price is 0")
    private Double price;

    @Override
    public Ticket toEntity() {
        Ticket ticket = new Ticket();
        ticket.setName(name);
        ticket.setDescription(description);
        ticket.setPrice(price);
        return ticket;
    }
}
