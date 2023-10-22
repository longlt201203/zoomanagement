package com.swp.ZooManagement.apis.areas;

import com.swp.ZooManagement.core.DtoBase;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class UpdateAreaDto implements DtoBase<Area> {
    @NotBlank(message = "code field cannot be blank")
    @Size(max = 10, message = "code cannot be more than 10 characters!")
    private String code;

    @NotBlank(message = "Name field cannot be blank")
    @Size(max = 100, message = "Name cannot be more than 100 characters!")
    private String name;

    @NotBlank(message = "Location field cannot be blank")
    @Size(max = 255, message = "Location cannot be more than 255 characters!")
    private String location;

    @Override
    public Area toEntity() {
        Area area = new Area();
        area.setCode(code);
        area.setName(name);
        area.setLocation(location);
        return area;
    }
}
