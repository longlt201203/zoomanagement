package com.swp.ZooManagement.apis.areas;

import com.swp.ZooManagement.core.AbstractZooManagementController;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/areas")
public class AreasController extends AbstractZooManagementController<Area, Integer, CreateAreaDto, UpdateAreaDto, FilterAreaDto, AreaResponseDto> {
}
