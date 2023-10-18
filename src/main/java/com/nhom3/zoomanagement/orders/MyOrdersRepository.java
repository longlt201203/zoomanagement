package com.nhom3.zoomanagement.orders;

import jakarta.transaction.Transactional;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Transactional
public interface MyOrdersRepository extends JpaRepository<MyOrder, String> {

    @Query("SELECT (o.createdAt) as date, SUM(od.quantity) as ticketQuantity, SUM(od.quantity * od.ticketPrice) as revenue " +
            "FROM OrderDetail od JOIN od.order o " +
            "WHERE (o.createdAt) BETWEEN :startDate AND :endDate " +
            "GROUP BY (o.createdAt) " +
            "ORDER BY (o.createdAt) ASC")
    List<Object[]> getTicketStatisticsPerDay(@Param("startDate") LocalDateTime startDate, @Param("endDate") LocalDateTime endDate);

    @Query("SELECT (o.createdAt) as date, SUM(od.quantity) as ticketQuantity, t.name " +
            "FROM OrderDetail od JOIN od.order o JOIN od.ticket t " +
            "WHERE (o.createdAt) BETWEEN :startDate AND :endDate AND t.name = :name " +
            "GROUP BY (o.createdAt), t.name " +
            "ORDER BY (o.createdAt) ASC")
    List<Object[]> getDetailTicketStatisticsPerDay(@Param("startDate") LocalDateTime startDate, @Param("endDate") LocalDateTime endDate, @Param("name") String name);

    @Query("SELECT (o.visitDate) as date, SUM(od.quantity) as visitQuantity FROM OrderDetail od " +
            "JOIN od.order o " +
            "WHERE o.visitDate BETWEEN :startDate AND :endDate " +
            "GROUP BY (o.visitDate) " +
            "ORDER BY (o.visitDate) ASC")
    List<Object[]> getVisitNumberPerDay(@Param("startDate") LocalDate startDate, @Param("endDate") LocalDate endDate);

    @Query("SELECT SUM(o.total) FROM MyOrder o " +
            "WHERE o.createdAt BETWEEN :startDate AND :endDate")
    Integer getTotalRevenue(@Param("startDate") LocalDateTime startDate, @Param("endDate") LocalDateTime endDate);
    
}
