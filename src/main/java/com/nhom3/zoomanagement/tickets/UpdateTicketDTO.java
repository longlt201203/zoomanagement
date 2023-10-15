package com.nhom3.zoomanagement.tickets;

import com.nhom3.zoomanagement.accounts.Account;
import com.nhom3.zoomanagement.utils.Enums;
import com.nhom3.zoomanagement.utils.validate_enum.ValueOfEnum;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UpdateTicketDTO {

    @NotBlank(message = "Name must be not blank")
    private String name;
    
    private String description;

    @Pattern(regexp = "[+-]?([0-9]*[.])?[0-9]+", message = "Price must be a number")
    @Min(value = 0, message = "The smallest price is 0")
    private String price;

    public Float parsePrice() {
        return Float.parseFloat(price);
    }

    public Ticket toTicket(Ticket presentTicket) {
        Ticket ticket = new Ticket();
        ticket.setName(this.getName());
        ticket.setDescription(this.getDescription());
        ticket.setPrice(this.parsePrice());
        ticket.setId(presentTicket.getId());
        ticket.setCreatedBy(presentTicket.getCreatedBy());
        return ticket;
    }
}
