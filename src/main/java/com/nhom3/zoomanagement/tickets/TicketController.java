package com.nhom3.zoomanagement.tickets;

import com.nhom3.zoomanagement.errors.BadRequestException;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("tickets")
public class TicketController implements ITicketController{
    @Autowired
    TicketService ticketService;
    
    @Override
    @GetMapping("get-all")
    public List<TicketDTO> get() {
        return ticketService.get();
    }

    @Override
    @GetMapping("get-by-Id/{id}")
    public TicketDTO get(@PathVariable("id") Integer id) throws BadRequestException {
        return ticketService.get(id);
    }

    @Override
    @PostMapping("create")
    public TicketDTO create(@RequestBody @Valid CreateTicketDTO dto) throws BadRequestException {
        return ticketService.create(dto);
    }

    @Override
    @PutMapping("update/{id}")
    public TicketDTO update(@PathVariable("id") Integer id, @RequestBody @Valid UpdateTicketDTO dto) throws BadRequestException {
        return ticketService.update(id, dto);
    }

    @Override
    @DeleteMapping("delete/{id}")
    public TicketDTO delete(@PathVariable("id") Integer id) throws BadRequestException {
        return ticketService.delete(id);
    }
}
