package com.swp.ZooManagement.apis.dashboard;

import com.swp.ZooManagement.utils.enums.SaleReportTypeEnum;
import lombok.Data;

import java.beans.ConstructorProperties;
import java.time.Instant;

@Data
public class GetSaleReportQueryParams {
    private SaleReportTypeEnum type;
    private Integer year;
    private Instant startDate;
    private Instant endDate;
    private Integer startYear;
    private Integer endYear;

    @ConstructorProperties({"type", "year", "startDate", "endDate", "startYear", "endYear"})
    public GetSaleReportQueryParams(SaleReportTypeEnum type, Integer year, Instant startDate, Instant endDate, Integer startYear, Integer endYear) {
        this.type = type;
        this.year = year;
        this.startDate = startDate;
        this.endDate = endDate;
        this.startYear = startYear;
        this.endYear = endYear;
    }
}
