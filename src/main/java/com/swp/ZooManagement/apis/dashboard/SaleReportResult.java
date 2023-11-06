package com.swp.ZooManagement.apis.dashboard;

import com.swp.ZooManagement.apis.tickets.GetTicketDistributionResult;
import lombok.Data;

import java.util.List;

@Data
public class SaleReportResult {
    private List<GetSaleReportResult> overallStatistics;
    private List<GetTicketDistributionResult> ticketDistribution;
}
