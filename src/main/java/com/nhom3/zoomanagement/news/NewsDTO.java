package com.nhom3.zoomanagement.news;

import com.nhom3.zoomanagement.accounts.AccountDTO;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class NewsDTO {
    private Integer id;
    private String content;
    private String title;
    private LocalDateTime createdAt;
    private AccountDTO creator;

    public static NewsDTO fromNews(News news, boolean hasCreator) {
        NewsDTO newsDTO = new NewsDTO();
        newsDTO.setId(news.getId());
        newsDTO.setContent(news.getContent());
        newsDTO.setTitle(news.getTitle());
        newsDTO.setCreatedAt(news.getCreatedAt());
        if (hasCreator) {
            newsDTO.setCreator(AccountDTO.fromAccount(news.getCreator(), false));
        }
        return newsDTO;
    }

    public static List<NewsDTO> fromNewsList(List<News> newsList, boolean hasCreator) {
        List<NewsDTO> newsDTOList = new ArrayList<>();
        for (News news : newsList) {
            NewsDTO newsDTO = fromNews(news, hasCreator);
            newsDTOList.add(newsDTO);
        }
        return newsDTOList;
    }
}
