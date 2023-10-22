package com.swp.ZooManagement.apis.news;

import com.swp.ZooManagement.core.AbstractZooManagementController;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/news")
public class NewsController extends AbstractZooManagementController<News, Integer, CreateNewsDto, UpdateNewsDto, FilterNewsDto, NewsResponseDto> {
}
