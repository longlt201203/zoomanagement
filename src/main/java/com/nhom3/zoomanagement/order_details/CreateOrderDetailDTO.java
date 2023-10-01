package com.nhom3.zoomanagement.order_details;

import com.nhom3.zoomanagement.utils.Enums;
import com.nhom3.zoomanagement.utils.validate_enum.ValueOfEnum;
import jakarta.validation.Valid;
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
    
    @Pattern(regexp = "[+-]?([0-9]*[.])?[0-9]+", message = "Price must be a number")
    @Min(value = 0, message = "The smallest price is 0")
    private String price;

    @NotBlank(message = "Type must be not blank")
    @ValueOfEnum(enumClass = Enums.TicketTypeEnum.class, message = "Ticket type is invalid")
    private String type;

    public Enums.TicketTypeEnum parseType() {
        return Enums.TicketTypeEnum.valueOf(type);
    }

    public Float parsePrice() {
        return Float.parseFloat(price);
    }

    public Integer parseQuantity() {
        return Integer.parseInt(quantity);
    }
}
