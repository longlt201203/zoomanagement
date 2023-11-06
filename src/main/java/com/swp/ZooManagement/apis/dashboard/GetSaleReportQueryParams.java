package com.swp.ZooManagement.apis.dashboard;

import lombok.Data;

import java.beans.ConstructorProperties;
import java.time.Instant;

@Data
public class GetSaleReportQueryParams {
    private Instant startDate;
    private Instant endDate;

    @ConstructorProperties({"startDate", "endDate"})
    public GetSaleReportQueryParams(Instant startDate, Instant endDate) {
        this.startDate = startDate;
        this.endDate = endDate;
    }
}
