package com.nhom3.zoomanagement.tickets;

import com.nhom3.zoomanagement.utils.Enums;
import com.nhom3.zoomanagement.utils.validate_enum.ValueOfEnum;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CreateTicketDTO {
    @NotBlank(message = "Type must be not blank")
    @ValueOfEnum(enumClass = Enums.TicketTypeEnum.class, message = "Type is invalid")
    private String type;

    @Min(value = 0, message = "The smallest price is 0")
    @Pattern(regexp = "[+-]?([0-9]*[.])?[0-9]+", message = "Price must be a number")
    private String price;

    public Enums.TicketTypeEnum parseType() {
        return Enums.TicketTypeEnum.valueOf(type);
    }

    public Float parsePrice() {
        return Float.parseFloat(price);
    }
}
