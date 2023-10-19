package com.nhom3.zoomanagement.news;

import com.nhom3.zoomanagement.accounts.Account;
import com.nhom3.zoomanagement.accounts.AccountDTO;
import com.nhom3.zoomanagement.accounts.AccountSpecificationBuilder;
import com.nhom3.zoomanagement.accounts.AccountsRepository;
import com.nhom3.zoomanagement.errors.BadRequestException;
import com.nhom3.zoomanagement.errors.ErrorReport;
import com.nhom3.zoomanagement.utils.search_filter.SearchCriteria;
import com.nhom3.zoomanagement.utils.search_filter.SearchRequestDTO;
import com.nhom3.zoomanagement.utils.search_filter.SearchResponseDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.security.access.prepost.PreAuthorize;
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
        News news = dto.toNews();
        NewsDTO newsDTO = NewsDTO.fromNews(newsRepository.save(news), true);
        return newsDTO;
    }

    @Override
    public NewsDTO update(Integer id, UpdateNewsDTO dto) throws BadRequestException {
        News news = newsRepository.findById(id).orElseThrow(() -> new BadRequestException(new ErrorReport("News not found")));
        news = dto.toNews(news);
        NewsDTO newsDTO = NewsDTO.fromNews(newsRepository.save(news), true);
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

    public SearchResponseDTO<NewsDTO> search(int pageNum, int pageSize, SearchRequestDTO dto) {
        List<SearchCriteria> searchCriteriaList = dto.getSearchCriteriaList();
        Pageable page = PageRequest.of(pageNum, pageSize, Sort.by("postedAt").descending());
        NewsSpecificationBuilder builder = new NewsSpecificationBuilder();

        if(searchCriteriaList != null) {
            searchCriteriaList.forEach(criteria -> {
                builder.with(criteria);
            });
        }

        Page<News> newsPage = newsRepository.findAll(builder.build(), page);

        List<NewsDTO> newsDTOS = NewsDTO.fromNewsList(newsPage.getContent(), true);
        Integer totalPages = newsPage.getTotalPages();
        Integer totalElements = Math.toIntExact(newsPage.getTotalElements());

        return new SearchResponseDTO<NewsDTO> (newsDTOS, totalPages, totalElements, pageNum);
    }
}
