package com.nhom3.zoomanagement.orders;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class TicketStatisticsDTO {
    private String name;
    private Integer quantity;
}
