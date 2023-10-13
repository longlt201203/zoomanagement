package com.nhom3.zoomanagement.order_details;

import com.nhom3.zoomanagement.orders.MyOrderDTO;
import com.nhom3.zoomanagement.tickets.TicketDTO;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class OrderDetailDTO {
    private Integer id;
    private Integer quantity;
    private Integer ticketPrice;
    private MyOrderDTO order;
    private TicketDTO ticket;

    public static OrderDetailDTO fromOrderDetail(OrderDetail orderDetail, boolean hasOrder, boolean hasTicket) {
        OrderDetailDTO orderDetailDTO = new OrderDetailDTO();
        orderDetailDTO.setId(orderDetail.getId());
        orderDetailDTO.setQuantity(orderDetail.getQuantity());
        orderDetailDTO.setTicketPrice(orderDetail.getTicketPrice());
        if (hasOrder) {
            orderDetailDTO.setOrder(MyOrderDTO.fromMyOrder(orderDetail.getOrder(), false));
        }
        if (hasTicket) {
            orderDetailDTO.setTicket(TicketDTO.fromTicket(orderDetail.getTicket(), false));
        }
        return orderDetailDTO;
    }

    public static List<OrderDetailDTO> fromOrderDetaillist(List<OrderDetail> orderDetailList, boolean hasOrder, boolean hasTicket) {
        List<OrderDetailDTO> orderDetailDTOList = new ArrayList<>();
        for (OrderDetail orderDetail : orderDetailList) {
            OrderDetailDTO orderDetailDTO = fromOrderDetail(orderDetail, hasOrder, hasTicket);
            orderDetailDTOList.add(orderDetailDTO);
        }
        return orderDetailDTOList;
    }
}
