package com.nhom3.zoomanagement.orders;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
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
    @Temporal(TemporalType.DATE)
    private Date dateToGo;

    @Column(updatable = false)
    @CreationTimestamp
    private Date createdAt;

    @Column
    private Float totalPrice;

    @OneToMany(mappedBy = "order", cascade = CascadeType.ALL)
    @JsonIgnoreProperties("order")
    private List<OrderDetail> details = new ArrayList<>();
    
    public MyOrder(String email, String name, Date dateToGo, Float totalPrice) {
        this.email = email;
        this.name = name;
        this.dateToGo = dateToGo;
        this.totalPrice = totalPrice;
    }

    public MyOrder(String email, String name, Date dateToGo, Float totalPrice, List<OrderDetail> details) {
        this.email = email;
        this.name = name;
        this.dateToGo = dateToGo;
        this.totalPrice = totalPrice;
        this.details = details;
    }
    
    public void addDetail(OrderDetail detail) {
        details.add(detail);
    }
}
