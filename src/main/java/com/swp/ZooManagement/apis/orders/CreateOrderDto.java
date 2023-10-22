package com.swp.ZooManagement.apis.orders;

import com.swp.ZooManagement.core.DtoBase;
import jakarta.validation.Valid;
import jakarta.validation.constraints.*;
import lombok.Data;

import java.time.Instant;
import java.util.ArrayList;
import java.util.List;

@Data
public class CreateOrderDto implements DtoBase<MyOrder> {
    @NotBlank(message = "Email must not be blank")
    @Email(message = "Email is invalid")
    private String email;

    @NotBlank(message = "Phone number must be not blank")
    @Pattern(regexp = "(84|0[35789])+([0-9]{8})\\b", message = "Invalid phone number")
    private String phone;

    @NotBlank(message = "Name must not be blank")
    @Size(max = 30, message = "Length of name must not exceed 30")
    private String name;

    @NotNull(message = "Visit date not null")
    private Instant visitDate;

    @NotNull(message = "Order details cannot be null")
    @Valid
    private List<CreateOrderDetailDto> details;

    @Override
    public MyOrder toEntity() {
        MyOrder order = new MyOrder();
        order.setEmail(email);
        order.setPhone(phone);
        order.setName(name);
        order.setVisitDate(visitDate);
        List<OrderDetail> orderDetails = new ArrayList<>();
        for (CreateOrderDetailDto createOrderDetailDto : details) {
            orderDetails.add(createOrderDetailDto.toEntity());
        }
        order.setDetails(orderDetails);
        return order;
    }
}
