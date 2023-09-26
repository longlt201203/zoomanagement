package com.nhom3.zoomanagement.order_details;

import com.nhom3.zoomanagement.orders.MyOrderDTO;
import com.nhom3.zoomanagement.utils.Enums;
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
    private Float price;
    private Enums.TicketTypeEnum type;
    private MyOrderDTO order;

    public static OrderDetailDTO fromOrderDetail(OrderDetail orderDetail, boolean hasOrder) {
        OrderDetailDTO orderDetailDTO = new OrderDetailDTO();
        orderDetailDTO.setId(orderDetail.getId());
        orderDetailDTO.setQuantity(orderDetail.getQuantity());
        orderDetailDTO.setPrice(orderDetail.getPrice());
        orderDetailDTO.setType(orderDetail.getType());
        if (hasOrder) {
            orderDetailDTO.setOrder(MyOrderDTO.fromMyOrder(orderDetail.getOrder(), false));
        }
        return orderDetailDTO;
    }

    public static List<OrderDetailDTO> fromOrderDetaillist(List<OrderDetail> orderDetailList, boolean hasOrder) {
        List<OrderDetailDTO> orderDetailDTOList = new ArrayList<>();
        for (OrderDetail orderDetail : orderDetailList) {
            OrderDetailDTO orderDetailDTO = fromOrderDetail(orderDetail, hasOrder);
            orderDetailDTOList.add(orderDetailDTO);
        }
        return orderDetailDTOList;
    }
}
