package com.swp.ZooManagement.apis.dashboard;

import java.time.Instant;

public interface GetSaleReportResult {
    Instant getDate();
    Instant getStartDate();
    Instant getEndDate();
    Integer getWeek();
    Integer getMonth();
    Long getTotalMoney();
    Long getTotalTicket();
}
