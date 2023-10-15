package com.nhom3.zoomanagement.news;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UpdateNewsDTO {
    @NotBlank(message = "Id must be not blank")
    @Pattern(regexp = "^\\d+$", message = "Id must be an integer")
    private String id;

    @NotBlank(message = "Content must be not blank")
    private String content;

    @NotBlank(message = "Title must be not blank")
    @Size(max = 100, message = "Length of title must not exceed 100")
    private String title;

    public Integer parseId() {
        return Integer.parseInt(id);
    }

    public News toNews(News presentNews) {
        News news = new News();
        news.setContent(this.getContent());
        news.setTitle(this.getTitle());
        news.setAuthor(presentNews.getAuthor());
        return news;
    }
}
