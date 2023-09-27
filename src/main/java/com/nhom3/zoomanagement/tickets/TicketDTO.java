package com.nhom3.zoomanagement.tickets;

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
    private Enums.TicketTypeEnum type;
    private Float price;

    public static TicketDTO fromTicket(Ticket ticket) {
        return new TicketDTO(ticket.getId(), ticket.getType(), ticket.getPrice());
    }

    public static List<TicketDTO> fromTicketList(List<Ticket> ticketList) {
        List<TicketDTO> ticketDTOList = new ArrayList<>();
        for (Ticket ticket : ticketList) {
            ticketDTOList.add(fromTicket(ticket));
        }
        return ticketDTOList;
    }
}
