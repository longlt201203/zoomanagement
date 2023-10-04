package com.nhom3.zoomanagement.animals;

import com.nhom3.zoomanagement.utils.Enums;
import com.nhom3.zoomanagement.utils.validate_enum.ValueOfEnum;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.PastOrPresent;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

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
    @DateTimeFormat(pattern = "yyyy-mm-dd")
    @PastOrPresent(message = "Date of Birth should be a valid date")
    private Date dob;
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
    @NotBlank(message = "species field cannot be blank")
    private String species;
    @NotBlank(message = "cage field cannot be blank")
    private String cage;
    private List<@NotBlank(message = "can not have blank field") String> imageList;
}
