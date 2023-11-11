package com.swp.ZooManagement.apis.orders;

import com.swp.ZooManagement.apis.tickets.Ticket;
import com.swp.ZooManagement.apis.tickets.TicketsRepository;
import com.swp.ZooManagement.apis.tickets.TicketsService;
import com.swp.ZooManagement.core.AbstractZooManagementService;
import com.swp.ZooManagement.errors.ZooManagementException;
import com.swp.ZooManagement.utils.enums.OrderStatusEnum;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Transactional
public class MyOrdersService extends AbstractZooManagementService<MyOrder, String, CreateOrderDto, UpdateOrderDto, FilterOrderDto> {
    @Autowired
    protected TicketsService ticketsService;

    @Override
    protected void beforeCreate(MyOrder entity) throws ZooManagementException {
        double sum = 0;
        for (OrderDetail detail : entity.getDetails()) {
            Ticket ticket = ticketsService.findById(detail.getTicket().getId());
            detail.setTicket(ticket);
            detail.setTicketPrice(ticket.getPrice());
            detail.setOrder(entity);
            sum += ticket.getPrice() * detail.getQuantity();
        }
        entity.setUsed(false);
        entity.setTotal(sum);
        entity.setStatus(OrderStatusEnum.PENDING);
    }

    @Override
    protected void beforeUpdate(MyOrder oldEntity, MyOrder newEntity) throws ZooManagementException {
        oldEntity.setStatus(newEntity.getStatus());
        oldEntity.setUsed(newEntity.isUsed());
    }

    @Override
    public MyOrder delete(String id) throws ZooManagementException {
        MyOrder order = findById(id);
        repository.delete(order);
        return order;
    }
}
