package com.nhom3.zoomanagement.areas;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CreateAreaDTO {
    @NotBlank(message = "code field cannot be blank")
    @Size(max = 10, message = "code cannot be more than 10 characters!")
    private String code;
    @NotBlank(message = "Name field cannot be blank")
    @Size(max = 100, message = "Name cannot be more than 100 characters!")
    private String name;
    @NotBlank(message = "Location field cannot be blank")
    @Size(max = 255, message = "Location cannot be more than 255 characters!")
    private String location;
}
