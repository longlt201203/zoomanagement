package com.nhom3.zoomanagement.areas;

import com.nhom3.zoomanagement.errors.AppServiceException;
import com.nhom3.zoomanagement.errors.ErrorReport;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class AreasService implements IAreasService {
    @Autowired
    AreasRepository areasRepository;
    @Override
    public List<AreaDTO> get() {
        List<Area> areaList = areasRepository.findAll();
        return  AreaDTO.fromAreaList(areaList, false, true);
    }

    @Override
    public AreaDTO get(Integer id) throws AppServiceException {
        Area area = areasRepository.findById(id).orElseThrow(() -> new AppServiceException(new ErrorReport("Area not found")));
        return  AreaDTO.fromArea(area, false, true);
    }

    @Override
    public AreaDTO create(CreateAreaDTO dto) throws AppServiceException {
        if(areasRepository.existsByCode(dto.getCode())){
            throw new AppServiceException(new ErrorReport("Area code already existed"));
        }
        Area area = new Area();
        area.setCode(dto.getCode());
        area.setName(dto.getName());
        area.setLocation(dto.getLocation());
        area = areasRepository.save(area);
        return AreaDTO.fromArea(area, false, true);
    }

    @Override
    public AreaDTO update(Integer id, UpdateAreaDTO dto) throws AppServiceException {
        Area area = areasRepository.findById(id).orElseThrow(() -> new AppServiceException(new ErrorReport("Area not found")));
        if(areasRepository.existsByCode(dto.getCode()) && !dto.getCode().matches(area.getCode())){
            throw new AppServiceException(new ErrorReport("Area code already existed"));
        }
        area.setCode(dto.getCode());
        area.setName(dto.getName());
        area.setLocation(dto.getLocation());
        area = areasRepository.save(area);
        return AreaDTO.fromArea(area, false, true);
    }

    @Override
    public AreaDTO delete(Integer id) {
        return null;
    }

}
