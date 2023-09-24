package com.nhom3.zoomanagement.orders;

import com.nhom3.zoomanagement.order_details.OrderDetail;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class MyOrder {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;

    private String email;

    private String name;

    private Date dateToGo;

    private Date createdAt;

    private Float totalPrice;

    @OneToMany(mappedBy = "order")
    private List<OrderDetail> details;
}
