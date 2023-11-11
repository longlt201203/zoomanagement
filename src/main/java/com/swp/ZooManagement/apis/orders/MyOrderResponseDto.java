package com.swp.ZooManagement.apis.orders;

import com.swp.ZooManagement.utils.enums.OrderStatusEnum;
import lombok.Data;

import java.time.Instant;
import java.util.List;

@Data
public class MyOrderResponseDto {
    private String id;
    private String email;
    private String phone;
    private String name;
    private Double total;
    private Instant createdAt;
    private Instant visitDate;
    private OrderStatusEnum status;
    private boolean isUsed;
    private List<OrderDetailResponseDto> details;
}
