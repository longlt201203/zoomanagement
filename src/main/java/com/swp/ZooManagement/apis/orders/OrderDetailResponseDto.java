package com.swp.ZooManagement.apis.orders;

import com.swp.ZooManagement.apis.tickets.TicketResponseDto;
import lombok.Data;

@Data
public class OrderDetailResponseDto {
    private Integer id;
    private Integer quantity;
    private Double ticketPrice;
    private TicketResponseDto ticket;
}
