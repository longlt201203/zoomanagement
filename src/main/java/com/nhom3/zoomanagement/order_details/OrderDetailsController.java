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
    @GetMapping("/")
    public List<OrderDetailDTO> get() {
        return orderDetailService.get();
    }

    @Override
    @GetMapping("/{id}")
    public OrderDetailDTO get(@PathVariable("id") Integer id) throws BadRequestException {
        return orderDetailService.get(id);
    }

    @Override
    @PostMapping("/")
    public OrderDetailDTO create(@RequestBody @Valid CreateOrderDetailDTO dto) throws BadRequestException {
        return orderDetailService.create(dto);
    }

    @Override
    @PutMapping("/{id}")
    public OrderDetailDTO update(@PathVariable("id") Integer id, @RequestBody @Valid UpdateOrderDetailDTO dto) throws BadRequestException {
        return orderDetailService.update(id, dto);
    }

    @Override
    @DeleteMapping("/{id}")
    public OrderDetailDTO delete(@PathVariable("id") Integer id) throws BadRequestException {
        return orderDetailService.delete(id);
    }
}
