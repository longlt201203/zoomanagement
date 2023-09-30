package com.nhom3.zoomanagement.orders;

import com.nhom3.zoomanagement.utils.validate_date_string.future_or_present.FutureOrPresentDate;
import com.nhom3.zoomanagement.utils.validate_date_string.valid_date.ValueOfDate;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
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
    
    @Size(message = "Details must contain at least 1 element")
    private List<CreateMyOrderDTO> details;

    public Date getDateToGo() {
        SimpleDateFormat DATE_FORMAT = new SimpleDateFormat("yyyy-MM-dd");
        try {
            return DATE_FORMAT.parse(dateToGo);
        } catch (ParseException e) {
            throw new RuntimeException(e);
        }
    }
}
