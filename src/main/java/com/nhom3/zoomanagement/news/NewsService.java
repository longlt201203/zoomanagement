package com.nhom3.zoomanagement.news;

import com.nhom3.zoomanagement.accounts.Account;
import com.nhom3.zoomanagement.accounts.AccountsRepository;
import com.nhom3.zoomanagement.errors.BadRequestException;
import com.nhom3.zoomanagement.errors.ErrorReport;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class NewsService implements INewsService {
    @Autowired
    NewsRepository newsRepository;
    
    @Autowired
    AccountsRepository accountsRepository;
    
    @Override
    public List<NewsDTO> get() {
        List<News> newsList = newsRepository.findAll();
        List<NewsDTO> newsDTOList = NewsDTO.fromNewsList(newsList, true);
        return newsDTOList;
    }

    @Override
    public NewsDTO get(Integer id) throws BadRequestException {
        News news = newsRepository.findById(id).orElseThrow(() -> new BadRequestException(new ErrorReport("News not found")));
        NewsDTO newsDTO = NewsDTO.fromNews(news, true);
        return null;
    }

    @Override
    public NewsDTO create(CreateNewsDTO dto) throws BadRequestException {
        Account account = accountsRepository.findById(dto.getAuthorId()).orElseThrow(() -> new BadRequestException(new ErrorReport("Account not found")));
        News news = dto.toNews();
        news.setAuthor(account);
        NewsDTO newsDTO = NewsDTO.fromNews(newsRepository.save(news), true);
        return newsDTO;
    }

    @Override
    public NewsDTO update(Integer id, UpdateNewsDTO dto) throws BadRequestException {
        News news = newsRepository.findById(id).orElseThrow(() -> new BadRequestException(new ErrorReport("News not found")));
        News updateNews = dto.toNews(news);
        NewsDTO newsDTO = NewsDTO.fromNews(newsRepository.save(updateNews), true);
        return newsDTO;
    }

    @Override
    public NewsDTO delete(Integer id) throws BadRequestException {
        if (newsRepository.existsById(id)) {
            newsRepository.deleteById(id);
        } else {
            throw new BadRequestException(new ErrorReport("News not found"));
        }
        return null;
    }
}
