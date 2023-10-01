package com.nhom3.zoomanagement.accounts;

import com.nhom3.zoomanagement.orders.CreateMyOrderDTO;
import com.nhom3.zoomanagement.orders.MyOrdersRepository;
import com.nhom3.zoomanagement.tickets.CreateTicketDTO;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AccountsController {
    @Autowired
    MyOrdersRepository myOrdersRepository;
    
    @PostMapping("create-order")
    public CreateMyOrderDTO createOrder(@Valid @RequestBody CreateMyOrderDTO createMyOrderDTO) {
        return createMyOrderDTO;
    }
    @PostMapping("create-ticket")
    public CreateTicketDTO createTicket(@Valid @RequestBody CreateTicketDTO createTicketDTO) {
        return createTicketDTO;
    }
}
