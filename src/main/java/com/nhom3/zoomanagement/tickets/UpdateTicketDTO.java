package com.nhom3.zoomanagement.tickets;

import com.nhom3.zoomanagement.utils.Enums;
import com.nhom3.zoomanagement.utils.ValueOfEnum;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UpdateTicketDTO {
    @NotBlank(message = "Id must be not blank")
    @Pattern(regexp = "^\\d+$", message = "Id must be a number")
    private Integer id;
    
    @NotBlank(message = "Type must be not blank")
    @ValueOfEnum(enumClass = Enums.TicketTypeEnum.class, message = "Type is invalid")
    private String type;

    @NotBlank(message = "Price must be not blank")
    @Pattern(regexp = "[+-]?([0-9]*[.])?[0-9]+", message = "Price must be a number")
    @Min(value = 0, message = "The smallest price is 0")
    private String price;

    public Enums.TicketTypeEnum getType() {
        return Enums.TicketTypeEnum.valueOf(type);
    }

    public Float getPrice() {
        return Float.parseFloat(price);
    }
}
