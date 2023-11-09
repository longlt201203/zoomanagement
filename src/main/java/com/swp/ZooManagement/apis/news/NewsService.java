package com.swp.ZooManagement.apis.news;

import com.swp.ZooManagement.apis.accounts.Account;
import com.swp.ZooManagement.apis.auth.AuthenticationService;
import com.swp.ZooManagement.core.AbstractZooManagementService;
import com.swp.ZooManagement.errors.ZooManagementException;
import com.swp.ZooManagement.utils.enums.NewsStatusEnum;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class NewsService extends AbstractZooManagementService<News, Integer, CreateNewsDto, UpdateNewsDto, FilterNewsDto> {
    @Autowired
    private AuthenticationService authenticationService;

    @Override
    public List<News> findAll(FilterNewsDto filterNewsDto) {
        Account currentUser = authenticationService.getCurrentUser();
        if (currentUser == null) {
            filterNewsDto.setStatus(NewsStatusEnum.PUBLISHED);
        }
        return super.findAll(filterNewsDto);
    }

    @Override
    protected void beforeCreate(News entity) throws ZooManagementException {
        entity.setStatus(NewsStatusEnum.HIDDEN);
    }

    @Override
    protected void beforeUpdate(News oldEntity, News newEntity) throws ZooManagementException {
        oldEntity.setTitle(newEntity.getTitle());
        oldEntity.setContent(newEntity.getContent());
        oldEntity.setStatus(newEntity.getStatus());
    }

    @Override
    public News delete(Integer id) throws ZooManagementException {
        return null;
    }
}
