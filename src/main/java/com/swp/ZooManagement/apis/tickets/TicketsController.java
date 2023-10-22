package com.swp.ZooManagement.apis.tickets;

import com.swp.ZooManagement.core.AbstractZooManagementController;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/tickets")
public class TicketsController extends AbstractZooManagementController<Ticket, Integer, CreateTicketDto, UpdateTicketDto, FilterTicketDto, TicketResponseDto> {
}
