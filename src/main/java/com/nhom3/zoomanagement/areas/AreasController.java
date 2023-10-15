package com.nhom3.zoomanagement.areas;

import com.nhom3.zoomanagement.errors.AppServiceException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/area")
public class AreasController implements IAreasController{
    @Autowired
    AreasService areasService;

    @Override
    public List<AreaDTO> get() {
        List<AreaDTO> areaDTOList = areasService.get();
        return areaDTOList;
    }

    @Override
    public AreaDTO get(Integer id) throws AppServiceException {
        AreaDTO areaDTO = areasService.get(id);
        return areaDTO;
    }

    @PreAuthorize("hasAnyAuthority({'STAFF', 'ADMIN'})")
    @Override
    public AreaDTO create(CreateAreaDTO dto) throws AppServiceException {
        AreaDTO areaDTO = areasService.create(dto);
        return areaDTO;
    }

    @PreAuthorize("hasAnyAuthority({'STAFF', 'ADMIN'})")
    @Override
    public AreaDTO update(Integer id, UpdateAreaDTO dto) throws AppServiceException {
        AreaDTO areaDTO = areasService.update(id, dto);
        return areaDTO;
    }

    @Override
    public AreaDTO delete(Integer id) {
        return null;
    }
}
