package com.nhom3.zoomanagement.orders;

import com.nhom3.zoomanagement.order_details.CreateOrderDetailDTO;
import com.nhom3.zoomanagement.utils.validate_date_string.future_or_present.FutureOrPresentDate;
import com.nhom3.zoomanagement.utils.validate_date_string.valid_date.ValueOfDate;
import jakarta.validation.Valid;
import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CreateMyOrderDTO {
    @NotBlank(message = "Email must not be blank")
    @Email(message = "Email is invalid")
    private String email;

    @NotBlank(message = "Name must not be blank")
    @Size(max = 30, message = "Length of name must not exceed 30")
    private String name;

    @ValueOfDate(message = "Date to go is invalid")
    @FutureOrPresentDate(message = "Date to go must be a date in the present or in the future")
    private String dateToGo;

    @NotEmpty(message = "Details must contain at least 1 element")
    @Valid
    private List<CreateOrderDetailDTO> details;

    public LocalDate parseDateToGo() {
        DateTimeFormatter DATE_FORMAT = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        return LocalDate.parse(dateToGo, DATE_FORMAT);
    }
}
