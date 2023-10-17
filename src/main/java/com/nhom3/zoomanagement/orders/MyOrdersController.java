package com.nhom3.zoomanagement.orders;

import com.nhom3.zoomanagement.errors.BadRequestException;
import com.nhom3.zoomanagement.utils.validate_date_string.valid_date.ValueOfDate;
import jakarta.mail.MessagingException;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("orders")
@Validated
public class MyOrdersController implements IMyOrdersController {
    @Autowired
    MyOrdersService myOrderService;

    @Override
    @GetMapping("get-all")
    public List<MyOrderDTO> get() {
        return myOrderService.get();
    }

    @Override
    @GetMapping("get-by-Id/{id}")
    public MyOrderDTO get(@PathVariable("id") String id) throws BadRequestException {
        return myOrderService.get(id);
    }

    @Override
    @PostMapping("create")
    public MyOrderDTO create(CreateMyOrderDTO dto) throws BadRequestException {
        return myOrderService.create(dto);
    }

    @Override
    @PutMapping("update/{id}")
    public MyOrderDTO update(@PathVariable("id") String id, @RequestBody @Valid Void dto) throws BadRequestException {
        return myOrderService.update(id, dto);
    }

    @Override
    @DeleteMapping("delete/{id}")
    public MyOrderDTO delete(@PathVariable("id") String id) throws BadRequestException {
        return myOrderService.delete(id);
    }

    @PostMapping("send-email-order-info/{id}")
    public boolean sendEmailOrderInfo(@PathVariable("id") String orderId) throws BadRequestException, MessagingException {
        return myOrderService.sendEmailOrderInfo(orderId);
    }

    @GetMapping("get-revenue/{startDate}")
    public Map<String, Object> getRevenue(@PathVariable("startDate") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate startDate) throws BadRequestException {
        return myOrderService.getRevenue(startDate);
    }

}
