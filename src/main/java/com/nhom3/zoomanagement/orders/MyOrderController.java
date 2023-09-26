package com.nhom3.zoomanagement.orders;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("orders")
public class MyOrderController {
    @Autowired
    MyOrderRepository myOrderRepository;
    
    @PostMapping("create")
    public MyOrder create() {
        MyOrder myOrder = new MyOrder();
        myOrder.setEmail("a@gmail.com");
        myOrder.setName("tho");
        return myOrderRepository.save(myOrder);
    }
}
