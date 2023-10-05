package com.nhom3.zoomanagement.orders;

import com.nhom3.zoomanagement.errors.BadRequestException;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("orders")
public class MyOrdersController implements IMyOrdersController {
    @Autowired
    MyOrdersService myOrderService;

    @Override
    @GetMapping("/")
    public List<MyOrderDTO> get() {
        return myOrderService.get();
    }

    @Override
    @GetMapping("/{id}")
    public MyOrderDTO get(@PathVariable("id") String id) throws BadRequestException {
        return myOrderService.get(id);
    }

    @Override
    @PostMapping("/")
    public MyOrderDTO create(CreateMyOrderDTO dto) throws BadRequestException {
        return myOrderService.create(dto);
    }

    @Override
    @PutMapping("/{id}")
    public MyOrderDTO update(@PathVariable("id") String id, @RequestBody @Valid Void dto) throws BadRequestException {
        return myOrderService.update(id, dto);
    }

    @Override
    @DeleteMapping("/{id}")
    public MyOrderDTO delete(@PathVariable("id") String id) throws BadRequestException {
        return myOrderService.delete(id);
    }
}
