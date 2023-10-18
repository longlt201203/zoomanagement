package com.nhom3.zoomanagement.orders;

import com.nhom3.zoomanagement.utils.validate_date_string.valid_date.ValueOfDate;
import lombok.Data;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

@Data
public class ReceiveDateDTO {
    @ValueOfDate(message = "Starting date is invalid")
    private String startDate;

    public LocalDate parseStartDate() {
        if(startDate == null) return null;
        DateTimeFormatter DATE_FORMAT = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        return LocalDate.parse(startDate, DATE_FORMAT);
    }
}
