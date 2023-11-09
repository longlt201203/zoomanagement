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

    @Query(value = "SELECT CAST(orderDate AS DATETIME) AS date, NULL AS month, NULL AS year, SUM(orderTotal) AS totalMoney, SUM(orderTicket) AS totalTicket \n" +
            "FROM (\n" +
            "\tSELECT a.id AS orderId, CAST(a.created_at AS DATE) AS orderDate, MIN(a.total) AS orderTotal, SUM(b.quantity) AS orderTicket\n" +
            "\tFROM my_order a\n" +
            "\tJOIN order_detail b\n" +
            "\tON a.id = b.order_id\n" +
            "\tWHERE CAST(a.created_at AS DATE) >= CAST(:startDate AS DATE) AND CAST(a.created_at AS DATE) <= CAST(:endDate AS DATE) AND a.status = 1\n" +
            "\tGROUP BY a.id, CAST(a.created_at AS DATE)\n" +
            ") c\n" +
            "GROUP BY orderDate", nativeQuery = true)
    List<GetSaleReportResult> getSalesReportByDay(@Param("startDate") Instant startDate, @Param("endDate") Instant endDate);

    @Query(value = "SELECT NULL AS date, month, :year AS year , SUM(orderTotal) AS totalMoney, SUM(orderTicket) AS totalTicket\n" +
            "FROM (\n" +
            "\tSELECT a.id AS orderId, MONTH(a.created_at) AS month, MIN(a.total) AS orderTotal, SUM(b.quantity) AS orderTicket\n" +
            "\tFROM my_order a\n" +
            "\tJOIN order_detail b\n" +
            "\tON a.id = b.order_id\n" +
            "\tWHERE a.status = 1 AND YEAR(a.created_at) = :year\n" +
            "\tGROUP BY a.id, MONTH(a.created_at)\n" +
            ") c\n" +
            "GROUP BY month", nativeQuery = true)
    List<GetSaleReportResult> getSalesReportByMonth(@Param("year") int year);

    @Query(value = "SELECT NULL AS date, NULL AS month, year, SUM(orderTotal) AS totalMoney, SUM(orderTicket) AS totalTicket \n" +
            "FROM (\n" +
            "\tSELECT a.id AS orderId, YEAR(a.created_at) AS year, MIN(a.total) AS orderTotal, SUM(b.quantity) AS orderTicket\n" +
            "\tFROM my_order a\n" +
            "\tJOIN order_detail b\n" +
            "\tON a.id = b.order_id\n" +
            "\tWHERE YEAR(a.created_at) >= :startYear AND YEAR(a.created_at) <= :endYear AND a.status = 1\n" +
            "\tGROUP BY a.id, YEAR(a.created_at)\n" +
            ") c\n" +
            "GROUP BY year", nativeQuery = true)
    List<GetSaleReportResult> getSalesReportByYear(@Param("startYear") int startYear, @Param("endYear") int endYear);
}
