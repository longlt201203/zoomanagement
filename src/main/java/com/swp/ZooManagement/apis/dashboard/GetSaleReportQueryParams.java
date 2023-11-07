package com.swp.ZooManagement.apis.dashboard;

import com.swp.ZooManagement.utils.enums.SaleReportTypeEnum;
import lombok.Data;

import java.beans.ConstructorProperties;
import java.time.Instant;

@Data
public class GetSaleReportQueryParams {
    private SaleReportTypeEnum type;
    private Integer month;
    private Integer year;
    private Instant startDate;
    private Instant endDate;

    @ConstructorProperties({"type", "month", "year", "startDate", "endDate"})
    public GetSaleReportQueryParams(SaleReportTypeEnum type, Integer month, Integer year, Instant startDate, Instant endDate) {
        this.type = type;
        this.year = year;
        this.startDate = startDate;
        this.endDate = endDate;
    }
}
