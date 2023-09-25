package com.nhom3.zoomanagement.order_details;

import com.nhom3.zoomanagement.orders.DTO.CreateOrderDetailDto;
import com.nhom3.zoomanagement.orders.MyOrder;
import com.nhom3.zoomanagement.tickets.Ticket;
import com.nhom3.zoomanagement.utils.Enums;
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

    private Integer quantity;

    private Float price;

    private Enums.TicketTypeEnum type;
    
    @ManyToOne
    private Ticket ticket;
    
    @ManyToOne(optional = false)
    @JoinColumn(referencedColumnName = "id")
    private MyOrder order;
    
    public void TakeCreateOrderDetailDto(CreateOrderDetailDto createOrderDetailDto) {
        setQuantity(createOrderDetailDto.getQuantity());
        setPrice(createOrderDetailDto.getPrice());
        setType(createOrderDetailDto.getType());
    }
}
