package com.swp.ZooManagement.apis.news;

import com.swp.ZooManagement.apis.accounts.AccountCreatorDto;
import com.swp.ZooManagement.utils.enums.NewsStatusEnum;
import lombok.Data;

import java.time.Instant;

@Data
public class NewsResponseDto {
    private Integer id;
    private String content;
    private String title;
    private Instant postedAt;
    private AccountCreatorDto author;
    private NewsStatusEnum status;
}
