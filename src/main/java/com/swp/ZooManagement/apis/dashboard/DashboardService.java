package com.swp.ZooManagement.apis.dashboard;

public interface DashboardService {
    ZooStatisticsResult getZooStatistics();
    SaleReportResult getSaleReport(GetSaleReportQueryParams params);
}
