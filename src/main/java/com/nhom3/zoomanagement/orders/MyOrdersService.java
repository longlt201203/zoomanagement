package com.nhom3.zoomanagement.orders;

import com.nhom3.zoomanagement.errors.BadRequestException;
import com.nhom3.zoomanagement.errors.ErrorReport;
import com.nhom3.zoomanagement.order_details.CreateOrderDetailDTO;
import com.nhom3.zoomanagement.order_details.OrderDetail;
import com.nhom3.zoomanagement.tickets.Ticket;
import com.nhom3.zoomanagement.tickets.TicketsRepository;
import com.nhom3.zoomanagement.utils.mail.DataMailDTO;
import com.nhom3.zoomanagement.utils.mail.MailService;
import jakarta.mail.MessagingException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

@Service
public class MyOrdersService implements IMyOrdersService {
    @Autowired
    MyOrdersRepository myOrdersRepository;

    @Autowired
    TicketsRepository ticketsRepository;

    @Autowired
    MailService mailService;

    @Override
    public List<MyOrderDTO> get() {
        List<MyOrder> myOrders = myOrdersRepository.findAll();
        List<MyOrderDTO> myOrderDTOS = MyOrderDTO.fromMyOrderList(myOrders, true);
        return myOrderDTOS;
    }

    @Override
    public MyOrderDTO get(String id) throws BadRequestException {
        MyOrder myOrder = myOrdersRepository.findById(id).orElseThrow(() -> new BadRequestException(new ErrorReport("Order not found")));
        MyOrderDTO myOrderDTO = MyOrderDTO.fromMyOrder(myOrder, true);
        return myOrderDTO;
    }

    @Override
    public MyOrderDTO create(CreateMyOrderDTO dto) throws BadRequestException {
        List<CreateOrderDetailDTO> createOrderDetails = dto.getDetails();
        
        List<Integer> ticketIds = createOrderDetails.stream().map(CreateOrderDetailDTO::parseTicketId).toList();
        List<Ticket> tickets = ticketsRepository.findAllById(ticketIds);
        
        if (tickets.size() != createOrderDetails.size())
            throw new BadRequestException(new ErrorReport("Tickets not found"));
        
        Integer total = createOrderDetails.stream().mapToInt(detail -> {
            Integer ticketPrice = tickets.stream().filter(ticket -> Objects.equals(ticket.getId(), detail.parseTicketId())).findFirst().orElse(tickets.get(0)).getPrice();
            return detail.parseQuantity() * ticketPrice;
        }).sum();
        
        MyOrder myOrder = dto.toMyOrder();
        
        List<OrderDetail> orderDetails = createOrderDetails.stream().map(detail -> {
            OrderDetail orderDetail = detail.toOrderDetail();
            Ticket ticket = tickets.stream().filter(ticketItem -> Objects.equals(ticketItem.getId(), detail.parseTicketId())).findFirst().orElse(tickets.get(0));
            orderDetail.setTicket(ticket);
            orderDetail.setTicketPrice(ticket.getPrice());
            orderDetail.setOrder(myOrder);
            return orderDetail;
        }).toList();
        myOrder.setDetails(orderDetails);
        myOrder.setTotal(total);
        MyOrderDTO myOrderDTO = MyOrderDTO.fromMyOrder(myOrdersRepository.save(myOrder), true);
        return myOrderDTO;
    }

    @Override
    public MyOrderDTO update(String id, Void dto) throws BadRequestException {
        return null;
    }

    @Override
    public MyOrderDTO delete(String id) throws BadRequestException {
        return null;
    }

    public boolean sendEmailOrderInfo(String orderId) throws BadRequestException, MessagingException {
        MyOrder order = myOrdersRepository.findById(orderId).orElseThrow(() -> new BadRequestException(new ErrorReport("Order not found")));

        DataMailDTO dataMail = new DataMailDTO();
        dataMail.setTo(order.getEmail());

        Map<String, Object> props = new HashMap<>();
        props.put("name", order.getName());
        props.put("orderId", order.getId());
        props.put("phone", order.getPhone());
        props.put("total", order.getTotal());
        props.put("visitDate", order.getVisitDate());

        dataMail.setProps(props);

        mailService.sendHtmlMail(dataMail);
        return true;
    }
}
