package com.nhom3.zoomanagement.orders;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class RevenueStatisticsDTO {
    private LocalDate date;
    private List<TicketStatisticsDTO> ticketDetails;
    private Integer ticketQuantity;
    private Integer revenue;
    private Integer visitQuantity;
}

