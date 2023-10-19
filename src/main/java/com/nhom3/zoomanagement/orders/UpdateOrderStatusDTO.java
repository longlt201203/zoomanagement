package com.nhom3.zoomanagement.orders;

import com.nhom3.zoomanagement.utils.Enums;
import com.nhom3.zoomanagement.utils.validate_enum.ValueOfEnum;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class UpdateOrderStatusDTO {
    @NotBlank(message = "Status can not be blank")
    @ValueOfEnum(message = "Invalid status", enumClass = Enums.OrderStatusEnum.class)
    private String status;

    public Enums.OrderStatusEnum parseStatus() {
        return Enums.OrderStatusEnum.valueOf(status);
    }
}
