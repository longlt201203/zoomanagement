package com.swp.ZooManagement.apis.mealrecords;

import com.swp.ZooManagement.apis.accounts.Account;
import com.swp.ZooManagement.apis.cagemeals.CageMeal;
import com.swp.ZooManagement.core.ResponsableEntity;
import com.swp.ZooManagement.utils.enums.MealStatusEnum;
import jakarta.persistence.*;
import lombok.Data;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.Instant;

@Entity
@EntityListeners(AuditingEntityListener.class)
@Data
public class MealRecord implements ResponsableEntity<MealRecordResponseDto> {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(nullable = false)
    private MealStatusEnum status = MealStatusEnum.NOT_FEED;

    @CreatedDate
    @Column(updatable = false)
    private Instant createdAt;

    @LastModifiedDate
    private Instant updatedAt;

    @LastModifiedBy
    @ManyToOne
    private Account updatedBy;

    @ManyToOne(optional = false)
    private CageMeal cageMeal;

    @Override
    public MealRecordResponseDto toResponseDto() {
        MealRecordResponseDto responseDto = new MealRecordResponseDto();
        responseDto.setId(id);
        responseDto.setStatus(status);
        responseDto.setCreatedAt(createdAt);
        responseDto.setUpdatedAt(updatedAt);
        responseDto.setUpdatedBy(updatedBy.toCreatorDto());
        return responseDto;
    }
}
