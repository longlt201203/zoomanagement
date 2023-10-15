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

    @NotBlank(message = "Phone number must be not blank")
    @Pattern(regexp = "(84|0[35789])+([0-9]{8})\\b", message = "Invalid phone number")
    private String phone;

    @NotBlank(message = "Name must not be blank")
    @Size(max = 30, message = "Length of name must not exceed 30")
    private String name;

    @ValueOfDate(message = "Visit date is invalid")
    @FutureOrPresentDate(message = "Visit date must be a date in the present or in the future")
    private String visitDate;

    @NotEmpty(message = "Details must contain at least 1 element")
    @Valid
    private List<CreateOrderDetailDTO> details;

    public LocalDate parseVisitDate() {
        DateTimeFormatter DATE_FORMAT = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        return LocalDate.parse(visitDate, DATE_FORMAT);
    }
    
    public MyOrder toMyOrder() {
        MyOrder myOrder = new MyOrder();
        myOrder.setEmail(this.getEmail());
        myOrder.setPhone(this.getPhone());
        myOrder.setName(this.getName());
        myOrder.setVisitDate(this.parseVisitDate());
        
        return myOrder;
    }
}
