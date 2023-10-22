package com.swp.ZooManagement.apis.tickets;

import com.swp.ZooManagement.core.FilterDtoBase;

public class FilterTicketDto extends FilterDtoBase<Ticket> {

    public FilterTicketDto(Integer page, Integer perPage) {
        super(page, perPage);
    }

    @Override
    public Ticket toEntity() {
        return null;
    }
}
