package com.nhom3.zoomanagement.orders;

import com.nhom3.zoomanagement.order_details.OrderDetailDTO;
import com.nhom3.zoomanagement.utils.Enums;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class MyOrderDTO {
    private String id;
    private String name;
    private String email;
    private String phone;
    private Integer total;
    private LocalDate visitDate;
    private LocalDateTime createdAt;
    private Enums.OrderStatus status;
    private List<OrderDetailDTO> details;

    public static MyOrderDTO fromMyOrder(MyOrder myOrder, boolean hasDetails) {
        MyOrderDTO myOrderDTO = new MyOrderDTO();
        myOrderDTO.setId(myOrder.getId());
        myOrderDTO.setName(myOrder.getName());
        myOrderDTO.setEmail(myOrder.getEmail());
        myOrderDTO.setPhone(myOrder.getPhone());
        myOrderDTO.setTotal(myOrder.getTotal());
        myOrderDTO.setVisitDate(myOrder.getVisitDate());
        myOrderDTO.setCreatedAt(myOrder.getCreatedAt());
        myOrderDTO.setStatus(myOrder.getOrderStatus());
        if (hasDetails) {
            myOrderDTO.setDetails(OrderDetailDTO.fromOrderDetaillist(myOrder.getDetails(), false, true));
        }
        return myOrderDTO;
    }

    public static List<MyOrderDTO> fromMyOrderList(List<MyOrder> myOrderList, boolean hasDetails) {
        List<MyOrderDTO> myOrderDTOList = new ArrayList<>();
        for (MyOrder myOrder : myOrderList) {
            MyOrderDTO myOrderDTO = fromMyOrder(myOrder, hasDetails);
            myOrderDTOList.add(myOrderDTO);
        }
        return myOrderDTOList;
    }
}
