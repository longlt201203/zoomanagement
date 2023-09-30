package com.nhom3.zoomanagement.local_files;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UpdateLocalFileDTO {
    @NotBlank(message = "Id must be not blank")
    private Integer id;
    
    @NotBlank(message = "Path must be not blank")
    private String path;
}
