package com.nhom3.zoomanagement.areas;

import com.nhom3.zoomanagement.animalspecies.AnimalSpecieDTO;
import com.nhom3.zoomanagement.cages.Cage;
import com.nhom3.zoomanagement.cages.CageDTO;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;
@Data
@NoArgsConstructor
@AllArgsConstructor
public class AreaDTO {
    public static AreaDTO fromArea(Area area, boolean hasCage) {
        AreaDTO areaDTO = new AreaDTO();
        areaDTO.setId(area.getId());
        areaDTO.setCode(area.getCode());
        areaDTO.setName(area.getName());
        areaDTO.setLocation(area.getLocation());
        if (hasCage) {
            areaDTO.setCageList(CageDTO.fromCageList(area.getCageList(), false, false, false));
        }
        return areaDTO;
    }

    public static List<AreaDTO> fromAreaList(List<Area> areaList, boolean hasCage) {
        List<AreaDTO> areaDTOList = new ArrayList<>();
        for (Area area : areaList) {
            AreaDTO areaDTO = fromArea(area, hasCage);
            areaDTOList.add(areaDTO);
        }
        return areaDTOList;
    }
    private Integer id;
    private String code;
    private String name;
    private String location;
    private List<CageDTO> cageList;

}
