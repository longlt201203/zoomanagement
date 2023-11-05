package com.swp.ZooManagement.apis.news;

import com.swp.ZooManagement.core.DtoBase;
import com.swp.ZooManagement.utils.IsEnum;
import com.swp.ZooManagement.utils.enums.NewsStatusEnum;
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

    @IsEnum(enumClass = NewsStatusEnum.class)
    private String status;

    @Override
    public News toEntity() {
        News news = new News();
        news.setContent(content);
        news.setTitle(title);
        news.setStatus(NewsStatusEnum.valueOf(status));
        return news;
    }
}
