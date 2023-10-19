package com.nhom3.zoomanagement.orders;

import com.nhom3.zoomanagement.errors.BadRequestException;
import com.nhom3.zoomanagement.news.NewsDTO;
import com.nhom3.zoomanagement.utils.search_filter.SearchRequestDTO;
import com.nhom3.zoomanagement.utils.search_filter.SearchResponseDTO;
import jakarta.mail.MessagingException;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("orders")
@Validated
public class MyOrdersController implements IMyOrdersController {
    @Autowired
    MyOrdersService myOrderService;

    @Override
    @PreAuthorize("hasAnyAuthority({'ADMIN', 'STAFF'})")
    public List<MyOrderDTO> get() {
        return myOrderService.get();
    }

    @Override
    public MyOrderDTO get(@PathVariable("id") String id) throws BadRequestException {
        return myOrderService.get(id);
    }

    @Override
    public MyOrderDTO create(CreateMyOrderDTO dto) throws BadRequestException {
        return myOrderService.create(dto);
    }

    @Override
    public MyOrderDTO update(@PathVariable("id") String id, @RequestBody @Valid Void dto) throws BadRequestException {
        return null;
    }

    @PostMapping ("update-status/{id}")
    public MyOrderDTO updateStatus(@PathVariable("id") String id, @RequestBody @Valid UpdateOrderStatusDTO dto) throws BadRequestException {
        return myOrderService.updateStatus(id,dto);
    }   

    @Override
    public MyOrderDTO delete(@PathVariable("id") String id) throws BadRequestException {
        return myOrderService.delete(id);
    }

    @PostMapping("send-email-order-info/{id}")
    public boolean sendEmailOrderInfo(@PathVariable("id") String orderId) throws BadRequestException, MessagingException {
        return myOrderService.sendEmailOrderInfo(orderId);
    }

    @PostMapping("get-revenue")
    public Map<String, Object> getRevenueIn7Days(@RequestBody @Valid ReceiveDateDTO dto) throws BadRequestException {
        return myOrderService.getRevenueIn7Days(dto.parseStartDate());
    }

    @PostMapping("search")
    public SearchResponseDTO<MyOrderDTO> search(@RequestParam(name = "pageNums", defaultValue = "0") int pageNum,
                                             @RequestParam(name = "pageSize", defaultValue = "10") int pageSize,
                                             @RequestBody SearchRequestDTO searchDTO) {
        return myOrderService.search(pageNum, pageSize, searchDTO);
    }

}
