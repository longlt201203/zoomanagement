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

    @Query(value = "SELECT orderId, noTicket, noMoney " +
            "FROM my_order a " +
            "JOIN (" +
            "SELECT order_detail.order_id AS orderId, SUM(ticket_id) AS noTicket, SUM(ticket_price) AS noMoney " +
            "FROM order_detail " +
            "LEFT JOIN ticket " +
            "ON order_detail.ticket_id = ticket.id " +
            "GROUP BY order_detail.order_id " +
            ") b " +
            "ON a.id = orderId " +
            "WHERE CAST(a.created_at AS DATE) >= CAST(:startDate AS DATE) AND CAST(a.created_at AS DATE) <= CAST(:endDate AS DATE) and a.status = 1", nativeQuery = true)
    List<GetSaleReportResult> getSalesReport(@Param("startDate") Instant startDate, @Param("endDate") Instant endDate);
}
