package com.nhom3.zoomanagement.orders;

import com.nhom3.zoomanagement.order_details.OrderDetail;
import com.nhom3.zoomanagement.utils.Enums;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@EntityListeners(AuditingEntityListener.class)
@Entity
public class MyOrder {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;

    @Column(nullable = false)
    private String email;

    @Column(nullable = false)
    private String phone;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private Integer total;
    
    @Column
    @Temporal(TemporalType.DATE)
    private LocalDate visitDate;

    @Column(updatable = false)
    @CreationTimestamp
    private LocalDateTime createdAt;
    
    @Column(columnDefinition = "VARCHAR(255) DEFAULT 'PENDING'")
    @Enumerated(EnumType.STRING)
    private Enums.OrderStatus orderStatus;
    
    @OneToMany(mappedBy = "order", cascade = CascadeType.ALL)
    private List<OrderDetail> details;
}
