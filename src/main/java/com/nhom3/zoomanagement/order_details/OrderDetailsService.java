package com.nhom3.zoomanagement.order_details;

import com.nhom3.zoomanagement.errors.BadRequestException;
import com.nhom3.zoomanagement.errors.ErrorReport;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OrderDetailsService implements IOrderDetailsService {
    @Autowired
    OrderDetailsRepository orderDetailsRepository;
    
    @Override
    public List<OrderDetailDTO> get() {
        List<OrderDetail> orderDetails = orderDetailsRepository.findAll();
        List<OrderDetailDTO> orderDetailDTOS = OrderDetailDTO.fromOrderDetaillist(orderDetails, true, true);
        return orderDetailDTOS;
    }

    @Override
    public OrderDetailDTO get(Integer id) throws BadRequestException {
        OrderDetail orderDetail = orderDetailsRepository.findById(id).orElseThrow(() -> new BadRequestException(new ErrorReport("Order detail not found")));
        OrderDetailDTO orderDetailDTO = OrderDetailDTO.fromOrderDetail(orderDetail, true, true);
        return orderDetailDTO;
    }

    @Override
    public OrderDetailDTO create(CreateOrderDetailDTO dto) throws BadRequestException {
        return null;
    }

    @Override
    public OrderDetailDTO update(Integer id, UpdateOrderDetailDTO dto) throws BadRequestException {
        return null;
    }

    @Override
    public OrderDetailDTO delete(Integer id) throws BadRequestException {
        return null;
    }
}
