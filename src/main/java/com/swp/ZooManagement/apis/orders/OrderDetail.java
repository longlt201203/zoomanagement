package com.swp.ZooManagement.apis.orders;

import com.swp.ZooManagement.apis.tickets.Ticket;
import com.swp.ZooManagement.core.ResponsableEntity;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
public class OrderDetail implements ResponsableEntity<OrderDetailResponseDto> {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column
    private Integer quantity;

    @Column
    private Double ticketPrice;

    @ManyToOne(optional = false)
    private Ticket ticket;

    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    @JoinColumn
    private MyOrder order;

    @Override
    public OrderDetailResponseDto toResponseDto() {
        OrderDetailResponseDto responseDto = new OrderDetailResponseDto();
        responseDto.setId(id);
        responseDto.setQuantity(quantity);
        responseDto.setTicketPrice(ticketPrice);
        ticket = getTicket();
        if (ticket != null) {
            responseDto.setTicket(ticket.toResponseDto());
        }
        return responseDto;
    }
}
