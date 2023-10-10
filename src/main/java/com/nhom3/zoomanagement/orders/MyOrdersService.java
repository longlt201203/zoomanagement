package com.nhom3.zoomanagement.orders;

import com.nhom3.zoomanagement.errors.BadRequestException;
import com.nhom3.zoomanagement.errors.ErrorReport;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MyOrdersService implements IMyOrdersService {
    @Autowired
    MyOrdersRepository myOrdersRepository;
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
        MyOrder myOrder = dto.toMyOrder();
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
}
