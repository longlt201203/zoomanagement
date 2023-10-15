package com.nhom3.zoomanagement.news;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CreateNewsDTO {
    @NotBlank(message = "Content must be not blank")
    private String content;
    
    @NotBlank(message = "Title must be not blank")
    @Size(max = 100, message = "Length of title must not exceed 100")
    private String title;
    
    public News toNews() {
        News news = new News();
        news.setContent(this.getContent());
        news.setTitle(this.getTitle());
        return news;
    }
}
