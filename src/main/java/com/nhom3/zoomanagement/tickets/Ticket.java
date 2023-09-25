package com.nhom3.zoomanagement.tickets;

import com.nhom3.zoomanagement.order_details.OrderDetail;
import com.nhom3.zoomanagement.utils.Enums;
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

    @Column
    @Enumerated(EnumType.STRING)
    private Enums.TicketTypeEnum type;

    @Column
    private Float price;
    
    @OneToMany(mappedBy = "ticket")
    private List<OrderDetail> orderDetailList;
}
