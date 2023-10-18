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

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.*;

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


    // 7days from the given day
    public List<RevenueStatisticsDTO> statisticsRevenue(LocalDate startDate) throws BadRequestException {
        if (startDate == null) {
            startDate = LocalDate.now().minusDays(6);
        }
        if (startDate.isAfter(LocalDate.now()))
            throw new BadRequestException(new ErrorReport("The start date can not be in the future"));
        List<TicketStatisticsDTO> statistics = new ArrayList<>();

        List<String> distinctTicketNames = ticketsRepository.findDistinctTicketNames();
        for (String name : distinctTicketNames) {
    
        }
        return null;
    }

    public Map<String, Object> getRevenueIn7Days(LocalDate startDate) throws BadRequestException {
        if (startDate == null) {
            startDate = LocalDate.now().minusDays(6);
        }
        LocalDate endDate = startDate.plusDays(6);
        List<Object[]> list1 = myOrdersRepository.getTicketStatisticsPerDay(startDate.atTime(0, 0), endDate.atTime(23, 59));
        List<Object[]> list2 = myOrdersRepository.getVisitNumberPerDay(startDate, endDate);
        Integer totalRevenue = myOrdersRepository.getTotalRevenue(startDate.atTime(0, 0), endDate.atTime(23, 59));
        List<String> ticketNames = ticketsRepository.findDistinctTicketNames();
        List<List<Object[]>> listDetails = new ArrayList<>();
        for (String ticketName : ticketNames) {
            List<Object[]> listDetail = myOrdersRepository.getDetailTicketStatisticsPerDay(startDate.atTime(0, 0), endDate.atTime(23, 59), ticketName);
            if (!listDetail.isEmpty())
                listDetails.add(listDetail);
        }
        Map<String, RevenueStatisticsDTO> map = new LinkedHashMap<>();

        for (int i = 0; i <= 6; i++) {
            LocalDate currentDate = startDate.plusDays(i);
            map.put(currentDate.toString(), new RevenueStatisticsDTO(currentDate, null, 0, 0, 0));
        }
        
        listDetails.forEach(listDetail -> {
            listDetail.forEach(detail -> {
                LocalDate currentDate = ((LocalDateTime) detail[0]).toLocalDate();
                if (map.get(currentDate.toString()).getTicketDetails() == null) {
                    map.get(currentDate.toString()).setTicketDetails(new ArrayList<>());
                }
                map.get(currentDate.toString()).getTicketDetails().add(new TicketStatisticsDTO(detail[2].toString(), Integer.parseInt(detail[1].toString())));
            });
        });

        list1.forEach(record -> {
            LocalDate currentDate = ((LocalDateTime) record[0]).toLocalDate();
            map.get(currentDate.toString()).setTicketQuantity(Integer.parseInt(record[1].toString()));
            map.get(currentDate.toString()).setRevenue(Integer.parseInt(record[2].toString()));
        });

        list2.forEach(record -> {
            LocalDate currentDate = (LocalDate) record[0];
            map.get(currentDate.toString()).setVisitQuantity(Integer.parseInt(record[1].toString()));
        });

        List<RevenueStatisticsDTO> statistics = map.keySet().stream().map(key -> {
            return map.get(key);
        }).toList();

        Map<String, Object> response = new HashMap<>();
        response.put("totalRevenue", totalRevenue);
        response.put("statistics", statistics);
        return response;
    }

}
