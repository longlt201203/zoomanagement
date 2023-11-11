package com.swp.ZooManagement.apis.tickets;

import com.swp.ZooManagement.core.FilterDtoBase;
import com.swp.ZooManagement.utils.enums.TicketStatusEnum;

import java.beans.ConstructorProperties;

public class FilterTicketDto extends FilterDtoBase<Ticket> {
    private TicketStatusEnum status;

    @ConstructorProperties({ "page", "perPage", "status" })
    public FilterTicketDto(Integer page, Integer perPage, TicketStatusEnum status) {
        super(page, perPage);
        this.status = status;
    }

    @Override
    public Ticket toEntity() {
        Ticket ticket = new Ticket();
        ticket.setStatus(status);
        return ticket;
    }
}
