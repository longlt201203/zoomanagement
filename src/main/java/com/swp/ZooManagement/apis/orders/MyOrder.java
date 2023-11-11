package com.swp.ZooManagement.apis.orders;

import com.swp.ZooManagement.core.ResponsableEntity;
import com.swp.ZooManagement.utils.enums.OrderStatusEnum;
import jakarta.persistence.*;
import lombok.Data;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.Instant;
import java.util.ArrayList;
import java.util.List;

@Entity
@Data
@EntityListeners(AuditingEntityListener.class)
public class MyOrder implements ResponsableEntity<MyOrderResponseDto> {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;

    @Column(nullable = false)
    private String email;

    @Column(nullable = false)
    private String phone;

    @Column(nullable = false, columnDefinition = "NVARCHAR(100)")
    private String name;

    @Column(nullable = false)
    private Double total;

    @Column(nullable = false)
    private Instant visitDate;

    @Column(nullable = false, updatable = false)
    @CreatedDate
    private Instant createdAt;

    @Column(nullable = false, columnDefinition = "SMALLINT DEFAULT 0")
    private OrderStatusEnum status;

    @Column(nullable = false, columnDefinition = "BIT DEFAULT 0")
    private boolean isUsed;

    @OneToMany(mappedBy = "order", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private List<OrderDetail> details;

    @Override
    public MyOrderResponseDto toResponseDto() {
        MyOrderResponseDto responseDto = new MyOrderResponseDto();
        responseDto.setId(id);
        responseDto.setEmail(email);
        responseDto.setPhone(phone);
        responseDto.setName(name);
        responseDto.setTotal(total);
        responseDto.setVisitDate(visitDate);
        responseDto.setCreatedAt(createdAt);
        responseDto.setStatus(status);
        responseDto.setUsed(isUsed);

        List<OrderDetailResponseDto> orderDetailDtoList = new ArrayList<>();
        for (OrderDetail detail : details) {
            orderDetailDtoList.add(detail.toResponseDto());
        }
        responseDto.setDetails(orderDetailDtoList);

        return responseDto;
    }
}
