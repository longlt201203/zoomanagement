package com.swp.ZooManagement.apis.cages;

import com.swp.ZooManagement.core.AbstractZooManagementController;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/cages")
public class CagesController extends AbstractZooManagementController<Cage, Integer, CreateCageDto, UpdateCageDto, FilterCageDto, CageResponseDto> {
}
