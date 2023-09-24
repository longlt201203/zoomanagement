package com.nhom3.zoomanagement.tickets;

import com.nhom3.zoomanagement.order_details.OrderDetail;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Ticket {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    
    private String type;
    
    private Float price;
    
    @OneToMany(mappedBy = "ticket")
    private List<OrderDetail> orderDetailList;
}
