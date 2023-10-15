package com.nhom3.zoomanagement.cages;

import com.nhom3.zoomanagement.errors.AppServiceException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
@RestController
@RequestMapping("/cage")
public class CagesController implements ICagesController{
    @Autowired
    CagesService cagesService;

    @Override
    public List<CageDTO> get() {
        List<CageDTO> cageDTOList = cagesService.get();
        return  cageDTOList;
    }

    @Override
    public CageDTO get(Integer id) throws AppServiceException {
        CageDTO cageDTO = cagesService.get(id);
        return  cageDTO;
    }

    @PreAuthorize("hasAnyAuthority({'STAFF', 'ADMIN'})")
    @Override
    public CageDTO create(CreateCageDTO dto) throws AppServiceException {
        CageDTO cageDTO = cagesService.create(dto);
        return  cageDTO;
    }

    @PreAuthorize("hasAnyAuthority({'STAFF', 'ADMIN'})")
    @Override
    public CageDTO update(Integer id, UpdateCageDTO dto) throws AppServiceException {
        CageDTO cageDTO = cagesService.update(id, dto);
        return  cageDTO;
    }

    @Override
    public CageDTO delete(Integer id) {
        return null;
    }
}
