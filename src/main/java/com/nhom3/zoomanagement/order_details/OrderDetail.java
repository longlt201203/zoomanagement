package com.nhom3.zoomanagement.order_details;

import com.nhom3.zoomanagement.orders.MyOrder;
import com.nhom3.zoomanagement.tickets.Ticket;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class OrderDetail {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column
    private Integer quantity;

    @Column
    private Integer ticketPrice;

    
    @ManyToOne(optional = false)
    private MyOrder order;
    
    @ManyToOne(optional = false)
    private Ticket ticket;
}
