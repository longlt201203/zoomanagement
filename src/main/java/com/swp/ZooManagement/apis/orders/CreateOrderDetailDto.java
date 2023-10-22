package com.swp.ZooManagement.apis.orders;

import com.swp.ZooManagement.apis.tickets.Ticket;
import com.swp.ZooManagement.core.DtoBase;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class CreateOrderDetailDto implements DtoBase<OrderDetail> {
    @Min(value = 1, message = "The smallest quantity is 1")
    private Integer quantity;

    private Integer ticketId;

    @Override
    public OrderDetail toEntity() {
        OrderDetail orderDetail = new OrderDetail();
        orderDetail.setQuantity(quantity);
        Ticket ticket = new Ticket();
        ticket.setId(ticketId);
        orderDetail.setTicket(ticket);
        return orderDetail;
    }
}
