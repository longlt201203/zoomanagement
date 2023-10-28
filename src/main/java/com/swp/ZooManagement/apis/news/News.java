package com.swp.ZooManagement.apis.news;

import com.swp.ZooManagement.apis.accounts.Account;
import com.swp.ZooManagement.apis.accounts.AccountCreatorDto;
import com.swp.ZooManagement.core.ResponsableEntity;
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

    @Column(nullable = false)
    private String title;

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
        if (author != null) {
            responseDto.setAuthor(author.toCreatorDto());
        }
        return responseDto;
    }
}
