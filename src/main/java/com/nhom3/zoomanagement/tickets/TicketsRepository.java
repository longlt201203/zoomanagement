package com.nhom3.zoomanagement.tickets;

import org.springframework.data.jpa.repository.JpaRepository;

public interface TicketsRepository extends JpaRepository<Ticket, Integer> {
}
