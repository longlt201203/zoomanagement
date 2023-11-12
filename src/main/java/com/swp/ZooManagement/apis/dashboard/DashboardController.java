package com.swp.ZooManagement.apis.dashboard;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@RequestMapping("/dashboard")
public interface DashboardController {
    @GetMapping("/zoo-statistics")
    ZooStatisticsResult getZooStatistics();

    @GetMapping("/sale-report")
    SaleReportResult getSaleReport(GetSaleReportQueryParams params);

    @GetMapping("/trainer-statistics")
    TrainerStatisticsResult getTrainerStatistics();
}
