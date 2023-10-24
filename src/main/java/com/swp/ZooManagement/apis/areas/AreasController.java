package com.swp.ZooManagement.apis.areas;

import com.swp.ZooManagement.core.AbstractZooManagementController;
import com.swp.ZooManagement.errors.ZooManagementException;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/areas")
public class AreasController extends AbstractZooManagementController<Area, Integer, CreateAreaDto, UpdateAreaDto, FilterAreaDto, AreaResponseDto> {
    @Override
    public List<AreaResponseDto> doGetMany(FilterAreaDto filter) throws ZooManagementException {
        AreasService service = (AreasService) this.service;
        List<GetAreasWithStatisticsResult> results = service.findAllWithStatistics();
        List<AreaResponseDto> responseDtoList = new ArrayList<>();
        for (GetAreasWithStatisticsResult result : results) {
            AreaResponseDto responseDto = new AreaResponseDto();
            responseDto.setId(result.getId());
            responseDto.setName(result.getName());
            responseDto.setCode(result.getCode());
            responseDto.setLocation(result.getLocation());
            responseDto.setNoCages(result.getCageCount() != null ? result.getCageCount() : 0);
            responseDto.setNoAnimals(result.getAnimalCount() != null ? result.getAnimalCount() : 0);
            responseDtoList.add(responseDto);
        }
        return responseDtoList;
    }
}
