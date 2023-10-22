package com.swp.ZooManagement.apis.news;

import com.swp.ZooManagement.core.DtoBase;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class UpdateNewsDto implements DtoBase<News> {
    @NotNull(message = "Content must not be null")
    @NotBlank(message = "Content must not be blank")
    private String content;

    @NotNull(message = "Title must not be null")
    @NotBlank(message = "Title must not be blank")
    private String title;

    @Override
    public News toEntity() {
        News news = new News();
        news.setContent(content);
        news.setTitle(title);
        return news;
    }
}
