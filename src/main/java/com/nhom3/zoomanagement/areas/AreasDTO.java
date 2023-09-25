package com.nhom3.zoomanagement.areas;

import com.nhom3.zoomanagement.cages.CagesDTO;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;
@Data
@NoArgsConstructor
@AllArgsConstructor
public class AreasDTO {
    public static AreasDTO fromArea(Area area, boolean hasCage) {
        AreasDTO areaDTO = new AreasDTO();
        areaDTO.setId(area.getId());
        areaDTO.setCode(area.getCode());
        areaDTO.setName(area.getName());
        areaDTO.setLocation(area.getLocation());
        if (hasCage) {
            areaDTO.setCageList(CagesDTO.fromCageList(area.getCageList(), false, false, false));
        }
        return areaDTO;
    }

    public static List<AreasDTO> fromAreaList(List<Area> areaList, boolean hasCage) {
        List<AreasDTO> areaDTOList = new ArrayList<>();
        for (Area area : areaList) {
            AreasDTO areaDTO = fromArea(area, hasCage);
            areaDTOList.add(areaDTO);
        }
        return areaDTOList;
    }
    private Integer id;
    private String code;
    private String name;
    private String location;
    private List<CagesDTO> cageList;

}
