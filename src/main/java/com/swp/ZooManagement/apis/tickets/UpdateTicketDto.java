package com.swp.ZooManagement.apis.tickets;

import com.swp.ZooManagement.core.DtoBase;
import com.swp.ZooManagement.utils.IsEnum;
import com.swp.ZooManagement.utils.enums.TicketStatusEnum;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class UpdateTicketDto implements DtoBase<Ticket> {
    @NotBlank(message = "Name must be not blank")
    private String name;

    private String description;

    @Min(value = 0, message = "The smallest price is 0")
    private Double price;

    @IsEnum(enumClass = TicketStatusEnum.class)
    private String status;

    @Override
    public Ticket toEntity() {
        Ticket ticket = new Ticket();
        ticket.setName(name);
        ticket.setDescription(description);
        ticket.setPrice(price);
        ticket.setStatus(TicketStatusEnum.valueOf(status));
        return ticket;
    }
}
