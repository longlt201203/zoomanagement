package com.nhom3.zoomanagement.order_details;

import com.nhom3.zoomanagement.utils.Enums;
import com.nhom3.zoomanagement.utils.ValueOfEnum;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;

public class CreateOrderDetailDTO {
    @NotBlank(message = "Id must be not blank")
    @Pattern(regexp = "^\\d+$", message = "Quantity must be a number")
    @Min(value = 1, message = "The smallest quantity is 1")
    private Integer quantity;
    
    @NotBlank(message = "Price must be not blank")
//    @Pattern(regexp = "^\\d+$", message = "Price must be a number")
    @Min(value = 0, message = "The smallest price is 0")
    private Float price;


    @ValueOfEnum(enumClass = Enums.TicketTypeEnum.class, message = "Ticket type is invalid")
    private String type;

    public Enums.TicketTypeEnum getType() {
        return Enums.TicketTypeEnum.valueOf(type);
    }
}
