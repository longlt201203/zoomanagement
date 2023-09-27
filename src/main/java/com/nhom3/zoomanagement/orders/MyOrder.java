package com.nhom3.zoomanagement.orders;

import com.nhom3.zoomanagement.order_details.OrderDetail;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
public class MyOrder {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;

    @Column
    private String email;

    @Column
    private String name;

    @Column
    private Float totalPrice;
    
    @Column
    @Temporal(TemporalType.DATE)
    private Date dateToGo;

    @Column(updatable = false)
    @CreationTimestamp
    private Date createdAt;

    @OneToMany(mappedBy = "order")
    private List<OrderDetail> details;
}
