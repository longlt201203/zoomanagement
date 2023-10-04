package com.nhom3.zoomanagement.local_files;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UpdateLocalFileDTO {
    @NotBlank(message = "Id must be not blank")
    @Pattern(regexp = "^\\d+$", message = "Id must be an integer")
    private String id;

    @NotBlank(message = "Path must be not blank")
    private String path;

    public Integer parseId() {
        return Integer.parseInt(id);
    }
}
