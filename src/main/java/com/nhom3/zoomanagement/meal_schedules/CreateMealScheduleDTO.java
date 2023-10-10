package com.nhom3.zoomanagement.meal_schedules;

import com.nhom3.zoomanagement.errors.BadRequestException;
import com.nhom3.zoomanagement.errors.ErrorReport;
import com.nhom3.zoomanagement.utils.validate_date_string.valid_date.ValueOfDate;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CreateMealScheduleDTO {
    @ValueOfDate(message = "Date is invalid")
    private String date;

    @NotNull(message = "cage field cannot be null")
    private Integer cageId;

    public LocalDate parseDate() throws BadRequestException {
        LocalDate localDate = LocalDate.now(ZoneId.systemDefault());
        DateTimeFormatter DATE_FORMAT = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        LocalDate inputDate = LocalDate.parse(date, DATE_FORMAT);
        if(inputDate.isBefore(localDate)){
            throw new BadRequestException(new ErrorReport("Date should be a valid date in the present or future"));
        }else {
            return inputDate;
        }
    }
}
