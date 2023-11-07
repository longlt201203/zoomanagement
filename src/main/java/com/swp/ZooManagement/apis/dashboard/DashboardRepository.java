package com.swp.ZooManagement.apis.dashboard;

import com.swp.ZooManagement.apis.orders.MyOrder;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.Instant;
import java.util.List;

public interface DashboardRepository extends JpaRepository<MyOrder, Integer> {
    @Query(value = "SELECT " +
            "(SELECT COUNT(*) FROM account WHERE role = 1) AS totalStaff, " +
            "(SELECT COUNT(*) FROM account WHERE role = 2) AS totalTrainer, " +
            "(SELECT COUNT(*) FROM animal WHERE status <> 3) AS totalAnimal, " +
            "(SELECT COUNT(*) FROM animal WHERE status = 1) AS totalAnimalSick, " +
            "(SELECT COUNT(*) FROM animal WHERE status = 2) AS totalAnimalInDanger, " +
            "(SELECT COUNT(*) FROM area) AS totalArea, " +
            "(SELECT COUNT(*) FROM cage) AS totalCage, " +
            "(SELECT COUNT(*) FROM news WHERE status = 0) AS totalNewsHidden, " +
            "(SELECT COUNT(*) FROM news WHERE status = 1) AS totalNewsPublished",
            nativeQuery = true)
    GetZooStatisticsResult getZooStatistics();

    @Query(value = "SELECT " +
            "CAST(CAST(created_at AS DATE) AS DATETIME) AS date, " +
            ":startDate AS startDate, " +
            ":endDate AS endDate, " +
            "NULL AS month, " +
            "NULL AS year, " +
            "SUM(total) AS totalMoney, " +
            "SUM(quantity) AS totalTicket " +
            "FROM " +
            "my_order " +
            "JOIN " +
            "order_detail ON my_order.id = order_detail.order_id " +
            "WHERE " +
            "CAST(created_at AS DATE) >= CAST(:startDate AS DATE) AND " +
            "CAST(created_at AS DATE) <= CAST(:endDate AS DATE) AND status = 1" +
            "GROUP BY " +
            "CAST(created_at AS DATE) " +
            "ORDER BY " +
            "date", nativeQuery = true)
    List<GetSaleReportResult> getSalesReportByWeek(@Param("startDate") Instant startDate, @Param("endDate") Instant endDate);

    @Query(value = "SET DATEFIRST 1;" +
            "SELECT " +
            "NULL AS date, " +
            "CAST(MIN(created_at) AS DATETIME) AS startDate, " +
            "CAST(DATEADD(DAY, 6, MIN(created_at)) AS DATETIME) AS endDate, " +
            "DATEPART(WEEK, created_at) - DATEPART(WEEK, DATEADD(DAY, 1-DAY(created_at), created_at)) + 1 AS week, " +
            "DATEPART(MONTH, created_at) AS month, " +
            "SUM(total) AS totalMoney, " +
            "SUM(quantity) AS totalTicket " +
            "FROM " +
            "my_order " +
            "JOIN " +
            "order_detail ON order_detail.order_id = my_order.id " +
            "WHERE " +
            "YEAR(created_at) = :year AND " +
            "DATEPART(MONTH, created_at) = :month AND status = 1" +
            "GROUP BY " +
            "DATEPART(MONTH, created_at), " +
            "DATEPART(WEEK, created_at) - DATEPART(WEEK, DATEADD(DAY, 1-DAY(created_at), created_at)) + 1 " +
            "ORDER BY " +
            "week;", nativeQuery = true)
    List<GetSaleReportResult> getSalesReportByMonth(@Param("year") int year, @Param("month") int month);

    @Query(value = "SELECT " +
            "NULL AS date, " +
            "NULL AS startDate, " +
            "NULL AS endDate, " +
            "NULL AS week, " +
            "DATEPART(MONTH, created_at) AS month, " +
            "SUM(total) AS totalMoney, " +
            "SUM(quantity) AS totalTicket " +
            "FROM " +
            "my_order " +
            "JOIN order_detail " +
            "ON order_detail.order_id = my_order.id " +
            "WHERE " +
            "YEAR(created_at) = 2023 AND status = 1" +
            "GROUP BY " +
            "DATEPART(MONTH, created_at) " +
            "ORDER BY " +
            "month", nativeQuery = true)
    List<GetSaleReportResult> getSalesReportByYear(@Param("year") int year);
}
