package com.swp.ZooManagement.apis.news;

import com.swp.ZooManagement.apis.accounts.Account;
import com.swp.ZooManagement.apis.accounts.AccountCreatorDto;
import com.swp.ZooManagement.core.ResponsableEntity;
import com.swp.ZooManagement.utils.enums.NewsStatusEnum;
import jakarta.persistence.*;
import lombok.Data;
import org.checkerframework.checker.units.qual.A;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.Instant;

@Entity
@EntityListeners(AuditingEntityListener.class)
@Data
public class News implements ResponsableEntity<NewsResponseDto> {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(nullable = false, columnDefinition = "NTEXT")
    private String content;

    @Column(nullable = false, columnDefinition = "NVARCHAR(255)")
    private String title;

    @Column(nullable = false, columnDefinition = "SMALLINT DEFAULT 0")
    private NewsStatusEnum status;

    @Column(nullable = false, updatable = false)
    @CreatedDate
    private Instant postedAt;

    @ManyToOne
    @CreatedBy
    private Account author;

    @Override
    public NewsResponseDto toResponseDto() {
        NewsResponseDto responseDto = new NewsResponseDto();
        responseDto.setId(id);
        responseDto.setContent(content);
        responseDto.setTitle(title);
        responseDto.setPostedAt(postedAt);
        responseDto.setStatus(status);
        if (author != null) {
            responseDto.setAuthor(author.toCreatorDto());
        }
        return responseDto;
    }
}
