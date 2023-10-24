package com.swp.ZooManagement.apis.cagemeals;

import com.swp.ZooManagement.core.AbstractZooManagementController;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/cage-meals")
public class CageMealsController extends AbstractZooManagementController<CageMeal, Integer, CreateCageMealDto, UpdateCageMealDto, FilterCageMealDto, CageMealResponseDto> {
}
