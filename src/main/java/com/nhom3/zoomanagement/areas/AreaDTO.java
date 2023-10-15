package com.nhom3.zoomanagement.areas;

import com.nhom3.zoomanagement.accounts.AccountDTO;
import com.nhom3.zoomanagement.cages.CageDTO;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
@Data
@NoArgsConstructor
@AllArgsConstructor
public class AreaDTO {
    public static AreaDTO fromArea(Area area, boolean hasCage, boolean hasCreatedBy) {
        AreaDTO areaDTO = new AreaDTO();
        areaDTO.setId(area.getId());
        areaDTO.setCode(area.getCode());
        areaDTO.setName(area.getName());
        areaDTO.setLocation(area.getLocation());
        if (hasCage) {
            areaDTO.setCageList(CageDTO.fromCageList(area.getCageList(), false, false, false, false, false, false));
        }
        if(hasCreatedBy){
            areaDTO.setCreatedBy(AccountDTO.fromAccount(area.getCreatedBy(), false));
        }
        return areaDTO;
    }

    public static List<AreaDTO> fromAreaList(List<Area> areaList, boolean hasCage, boolean hasCreatedBy) {
        List<AreaDTO> areaDTOList = new ArrayList<>();
        for (Area area : areaList) {
            AreaDTO areaDTO = fromArea(area, hasCage, hasCreatedBy);
            areaDTOList.add(areaDTO);
        }
        return areaDTOList;
    }
    private Integer id;
    private String code;
    private String name;
    private String location;
    private AccountDTO createdBy;
    private List<CageDTO> cageList;

}
