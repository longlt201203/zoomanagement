package com.nhom3.zoomanagement.animals;

import com.nhom3.zoomanagement.errors.BadRequestException;
import com.nhom3.zoomanagement.errors.ErrorReport;
import com.nhom3.zoomanagement.utils.Enums;
import com.nhom3.zoomanagement.utils.validate_date_string.valid_date.ValueOfDate;
import com.nhom3.zoomanagement.utils.validate_enum.ValueOfEnum;
import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UpdateAnimalDTO {
    @NotBlank(message = "Name field cannot be blank")
    @Size(max = 100, message = "Name cannot be more than 100 characters!")
    private String name;
    @Size(max = 100, message = "Nation cannot be more than 100 characters!")
    private String nation;
    @ValueOfDate(message = "Date is invalid")
    //@PastOrPresent(message = "Date of Birth should be a valid date")
    private String dob;
    @NotBlank(message = "Gender field cannot be blank")
    @ValueOfEnum(enumClass = Enums.AnimalGenderEnum.class, message = "gender must be any of enum list")
    private String gender;
    @NotBlank(message = "status field cannot be blank")
    @ValueOfEnum(enumClass = Enums.AnimalStatusEnum.class, message = "status must be any of enum list")
    private String status;
    @Size(max = 255, message = "Description cannot be more than 255 characters!")
    private String description;
    @Size(max = 255, message = "Note cannot be more than 255 characters!")
    private String note;
    @NotNull(message= "Animal Species field can not be empty")
    private Integer speciesId;
    @NotNull(message = "cage field cannot be blank")
    private Integer cageId;
    @NotEmpty(message = "imageList must contain at least 1 element")
    private List<@NotBlank(message = "imageList can not have blank field") String> imageList;

    public LocalDate parseDob() throws BadRequestException {
        LocalDate localDate = LocalDate.now(ZoneId.systemDefault());
        DateTimeFormatter DATE_FORMAT = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        LocalDate inputDob = LocalDate.parse(dob, DATE_FORMAT);
        if(inputDob.isAfter(localDate)){
            throw new BadRequestException(new ErrorReport("Date of Birth should be a valid date in the past or present"));
        }else {
            return inputDob;
        }
    }
}
