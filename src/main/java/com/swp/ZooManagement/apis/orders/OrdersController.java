package com.swp.ZooManagement.apis.orders;

import com.swp.ZooManagement.core.AbstractZooManagementController;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/orders")
public class OrdersController extends AbstractZooManagementController<MyOrder, String, CreateOrderDto, UpdateOrderDto, FilterOrderDto, MyOrderResponseDto> {
}
