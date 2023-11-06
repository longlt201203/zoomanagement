package com.swp.ZooManagement.apis.tickets;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.Instant;
import java.util.List;
import java.util.Optional;

public interface TicketsRepository extends JpaRepository<Ticket, Integer> {
    Optional<Ticket> findByName(String name);
    @Query(value = "SELECT ticketId, c.name AS ticketName, quantity, money " +
            "FROM ticket c " +
            "JOIN ( " +
            "SELECT a.ticket_id AS ticketId, SUM(a.quantity) AS quantity, SUM(a.ticket_price*a.quantity) AS money " +
            "FROM order_detail a " +
            "JOIN my_order b " +
            "ON a.order_id = b.id " +
            "WHERE CAST(b.created_at AS DATE) >= CAST(:startDate AS DATE) AND CAST(b.created_at AS DATE) <= CAST(:endDate AS DATE) AND b.status = 1 " +
            "GROUP BY a.ticket_id " +
            ") d " +
            "ON c.id = ticketId", nativeQuery = true)
    List<GetTicketDistributionResult> getTicketDistribution(@Param("startDate") Instant startDate, @Param("endDate") Instant endDate);
}
