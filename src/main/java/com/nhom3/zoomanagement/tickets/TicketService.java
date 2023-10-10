package com.nhom3.zoomanagement.tickets;

import com.nhom3.zoomanagement.errors.BadRequestException;
import com.nhom3.zoomanagement.errors.ErrorReport;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TicketService implements ITicketService {
    @Autowired
    TicketsRepository ticketsRepository;
    
    @Override
    public List<TicketDTO> get() {
        List<Ticket> tickets = ticketsRepository.findAll();
        List<TicketDTO> ticketDTOs = TicketDTO.fromTicketList(tickets);
        return ticketDTOs;
    }

    @Override
    public TicketDTO get(Integer id) throws BadRequestException {
        Ticket ticket = ticketsRepository.findById(id).orElseThrow(() -> new BadRequestException(new ErrorReport("Ticket not found")));;
        TicketDTO ticketDTO = TicketDTO.fromTicket(ticket);
        return ticketDTO;
    }

    @Override
    public TicketDTO create(CreateTicketDTO dto) throws BadRequestException {
        Ticket ticket = dto.toTicket();
        TicketDTO ticketDTO = TicketDTO.fromTicket(ticketsRepository.save(ticket));
        return ticketDTO;
    }

    @Override
    public TicketDTO update(Integer id, UpdateTicketDTO dto) throws BadRequestException {
        Ticket ticket = ticketsRepository.findById(id).orElseThrow(() -> new BadRequestException(new ErrorReport("Ticket not found")));
        Ticket updateTicket = dto.toTicket();
        TicketDTO ticketDTO = TicketDTO.fromTicket(ticketsRepository.save(updateTicket));
        return ticketDTO;
    }

    @Override
    public TicketDTO delete(Integer id) throws BadRequestException {
        if (ticketsRepository.existsById(id)) {
            ticketsRepository.deleteById(id);
        } else {
            throw new BadRequestException(new ErrorReport("Ticket not found"));
        }
        return null;
    }
}