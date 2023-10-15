package com.nhom3.zoomanagement.tickets;

import com.nhom3.zoomanagement.accounts.Account;
import com.nhom3.zoomanagement.accounts.AccountDTO;
import com.nhom3.zoomanagement.utils.Enums;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class TicketDTO {
    private Integer id;
    private String name;
    private String description;
    private Float price;
    private AccountDTO createdBy;

    public static TicketDTO fromTicket(Ticket ticket, boolean hasCreatedBy) {
        TicketDTO ticketDTO = new TicketDTO();
        ticketDTO.setId(ticket.getId());
        ticketDTO.setName(ticket.getName());
        ticketDTO.setDescription(ticket.getDescription());
        ticketDTO.setPrice(ticket.getPrice());
        if(hasCreatedBy) {
            ticketDTO.setCreatedBy(AccountDTO.fromAccount(ticket.getCreatedBy(), false, false));
        }
        return ticketDTO;
    }

    public static List<TicketDTO> fromTicketList(List<Ticket> ticketList, boolean hasCreatedBy) {
        List<TicketDTO> ticketDTOList = new ArrayList<>();
        for (Ticket ticket : ticketList) {
            ticketDTOList.add(fromTicket(ticket, hasCreatedBy));
        }
        return ticketDTOList;
    }
}
