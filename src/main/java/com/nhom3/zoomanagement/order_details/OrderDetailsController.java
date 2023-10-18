package com.nhom3.zoomanagement.order_details;

import com.nhom3.zoomanagement.errors.BadRequestException;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("order-details")
public class OrderDetailsController implements IOrderDetailsController {
    @Autowired
    OrderDetailsService orderDetailService;
    
    @Override
    @GetMapping("get-all")
    public List<OrderDetailDTO> get() {
        return orderDetailService.get();
    }

    @Override
    @GetMapping("get-by-Id/{id}")
    public OrderDetailDTO get(@PathVariable("id") Integer id) throws BadRequestException {
        return orderDetailService.get(id);
    }

    @Override
    @PostMapping("create")
    public OrderDetailDTO create(@RequestBody @Valid CreateOrderDetailDTO dto) throws BadRequestException {
        return null;
    }

    @Override
    @PutMapping("update/{id}")
    public OrderDetailDTO update(@PathVariable("id") Integer id, @RequestBody @Valid UpdateOrderDetailDTO dto) throws BadRequestException {
        return null;
    }

    @Override
    @DeleteMapping("delete/{id}")
    public OrderDetailDTO delete(@PathVariable("id") Integer id) throws BadRequestException {
        return null;
    }
}
