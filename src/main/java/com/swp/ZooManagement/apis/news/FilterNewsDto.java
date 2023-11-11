package com.swp.ZooManagement.apis.news;

import com.swp.ZooManagement.apis.accounts.Account;
import com.swp.ZooManagement.core.FilterDtoBase;
import com.swp.ZooManagement.utils.IsEnum;
import com.swp.ZooManagement.utils.enums.NewsStatusEnum;

import java.beans.ConstructorProperties;

public class FilterNewsDto extends FilterDtoBase<News> {
    protected String email;
    private NewsStatusEnum status;

    public void setStatus(NewsStatusEnum status) {
        this.status = status;
    }

    public FilterNewsDto(Integer page, Integer perPage, String email, NewsStatusEnum status) {
        super(page, perPage);
        this.email = email;
        this.status = status;
    }

    @Override
    public News toEntity() {
        News news = new News();
        news.setStatus(status);
        Account account = new Account();
        account.setEmail(email);
        news.setAuthor(account);
        return news;
    }
}
