package com.swp.ZooManagement.apis.news;

import com.swp.ZooManagement.core.AbstractZooManagementService;
import com.swp.ZooManagement.errors.ZooManagementException;
import org.springframework.stereotype.Service;

@Service
public class NewsService extends AbstractZooManagementService<News, Integer, CreateNewsDto, UpdateNewsDto, FilterNewsDto> {
    @Override
    protected void beforeCreate(News entity) throws ZooManagementException {

    }

    @Override
    protected void beforeUpdate(News oldEntity, News newEntity) throws ZooManagementException {
        oldEntity.setTitle(newEntity.getTitle());
        oldEntity.setContent(newEntity.getContent());
    }

    @Override
    public News delete(Integer id) throws ZooManagementException {
        return null;
    }
}
