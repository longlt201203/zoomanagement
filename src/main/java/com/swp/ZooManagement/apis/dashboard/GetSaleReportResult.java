package com.swp.ZooManagement.apis.dashboard;

import java.time.Instant;

public interface GetSaleReportResult {
    Instant getDate();
    Integer getMonth();
    Integer getYear();
    Long getTotalMoney();
    Long getTotalTicket();
}
