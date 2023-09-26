package com.nhom3.zoomanagement.orders;

import com.nhom3.zoomanagement.order_details.OrderDetailDTO;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class MyOrderDTO {
    private String id;
    private String email;
    private String name;
    private Float totalPrice;
    private Date dateToGo;
    private Date createdAt;
    private List<OrderDetailDTO> details;

    public static MyOrderDTO fromMyOrder(MyOrder myOrder, boolean hasDetails) {
        MyOrderDTO myOrderDTO = new MyOrderDTO();
        myOrderDTO.setId(myOrder.getId());
        myOrderDTO.setName(myOrder.getName());
        myOrderDTO.setTotalPrice(myOrder.getTotalPrice());
        myOrderDTO.setDateToGo(myOrder.getDateToGo());
        myOrderDTO.setCreatedAt(myOrder.getCreatedAt());
        if (hasDetails) {
            myOrderDTO.setDetails(OrderDetailDTO.fromOrderDetaillist(myOrder.getDetails(), false));
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
