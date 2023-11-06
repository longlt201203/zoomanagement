package com.swp.ZooManagement.apis.dashboard;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class DashboardControllerImpl implements DashboardController {
    @Autowired
    private DashboardService dashboardService;

    @Override
    public ZooStatisticsResult getZooStatistics() {
        return dashboardService.getZooStatistics();
    }

    @Override
    public SaleReportResult getSaleReport(GetSaleReportQueryParams params) {
        return dashboardService.getSaleReport(params);
    }
}
