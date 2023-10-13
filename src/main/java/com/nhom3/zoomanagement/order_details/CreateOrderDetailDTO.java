package com.nhom3.zoomanagement.order_details;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CreateOrderDetailDTO {
    @NotBlank(message = "Quantity must be not blank")
    @Pattern(regexp = "^\\d+$", message = "Quantity must be an integer")
    @Min(value = 1, message = "The smallest quantity is 1")
    private String quantity;

    @NotBlank(message = "TicketPrice must be not blank")
    @Pattern(regexp = "[+-]?([0-9]*[.])?[0-9]+", message = "Price must be a number")
    @Min(value = 0, message = "The smallest price is 0")
    private String ticketPrice;

    @NotBlank(message = "TicketId must be not blank")
    @Pattern(regexp = "^\\d+$", message = "TicketId must be an integer")
    private String ticketId;

    public Integer parseTicketPrice() {
        return Integer.parseInt(ticketPrice);
    }
    public Integer parseTicketId() {
        return Integer.parseInt(ticketId);
    }

    public Integer parseQuantity() {
        return Integer.parseInt(quantity);
    }
    
    public OrderDetail toOrderDetail() {
        OrderDetail orderDetail = new OrderDetail();
        orderDetail.setQuantity(this.parseQuantity());
        orderDetail.setTicketPrice(this.parseTicketPrice());
        return orderDetail;
    }
}
